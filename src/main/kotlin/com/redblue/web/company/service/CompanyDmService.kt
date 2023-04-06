package com.redblue.web.company.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.lowagie.text.Document
import com.lowagie.text.pdf.BaseFont
import com.lowagie.text.pdf.PdfImportedPage
import com.lowagie.text.pdf.PdfReader
import com.lowagie.text.pdf.PdfWriter
import com.redblue.web.company.generator.DmContextGenerator
import com.redblue.web.company.model.Company
import com.redblue.web.company.model.Executive
import com.redblue.web.dm.model.DmHistory
import com.redblue.web.dm.service.DmService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.thymeleaf.TemplateEngine
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import org.xhtmlrenderer.pdf.ITextRenderer
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


@Service
class CompanyDmService(
	private val dmService: DmService,
	private val companyService: CompanyService,
	private val contextGenerator: DmContextGenerator
) {

	fun generate(companies: List<Company>, user: LawFirmUser, templateId: Int, startDate: Date?, endDate: Date?): ByteArrayInputStream {

		val readers = mutableListOf<PdfReader>()
		val histories = mutableListOf<DmHistory>()
		for (company in companies) {
			val outputStream = ByteArrayOutputStream()
			val renderer = ITextRenderer()

			val executives = company.executives?.filter {
				it.expiredAt != null && (it.expiredAt >= startDate && it.expiredAt <= endDate)
			}?.toMutableList()

			val dateFormat = SimpleDateFormat("yyyy-MM-dd")
			val year = LocalDate.parse(dateFormat.format(startDate)).year
			val auditorEndDate: Date = when(company.settlementMonth) {
				10 -> dateFormat.parse("$year-01-31")
				11 -> dateFormat.parse("$year-02-${getDaysInMonth(2, year)}")
				12 -> dateFormat.parse("$year-03-31")
				else -> {
					val m = company.settlementMonth!! + 3
					dateFormat.parse("$year-$m-${getDaysInMonth(m, year)}")
				}
			}

			val cal = Calendar.getInstance()
			cal.time = auditorEndDate
			cal.add(Calendar.MONTH, -3)
			val startYear = LocalDate.parse(dateFormat.format(cal.time)).year
			val startMonth = LocalDate.parse(dateFormat.format(cal.time)).month.value.let {
				if(it < 10) {
					"0$it"
				} else {
					it.toString()
				}
			}
			val auditorStartDate: Date = dateFormat.parse("$startYear-$startMonth-01")
			val executiveIds = executives?.map { it.id } ?: emptyList()
			company.executives?.filter {
				it.expiredAt != null && (it.expiredAt in auditorStartDate..auditorEndDate) && it.expiredAt >= Date() && it.position.equals("감사")
			}?.forEach {
				if(!executiveIds.contains(it.id)) {
					executives?.add(it)
				}
			}

			val master = company.executives?.filter {
				it.expiredAt != null && (it.position.equals("대표이사") || it.position.equals("사내이사") || it.position.equals("공동대표이사"))
			}

			val sortedExecutives = mutableListOf<Executive>()
			executives?.forEach {
				if(it.position.equals("대표이사")) {
					sortedExecutives.add(it)
				}
			}

			executives?.forEach {
				if(it.position.equals("사내이사")) {
					sortedExecutives.add(it)
				}
			}

			executives?.forEach {
				if(it.position.equals("사외이사")) {
					sortedExecutives.add(it)
				}
			}

			executives?.forEach {
				if(it.position.equals("기타비상무이사")) {
					sortedExecutives.add(it)
				}
			}

			executives?.forEach {
				if(it.position.equals("감사")) {
					sortedExecutives.add(it)
				}
			}
			executives?.filter {
				!it.position.equals("대표이사") && !it.position.equals("사내이사") && !it.position.equals("사외이사") && !it.position.equals("기타비상무이사") && !it.position.equals("감사")
			}?.forEach {
				sortedExecutives.add(it)
			}


			renderer.setDocumentFromString(this.parseThymeleafTemplate(company, sortedExecutives, user, templateId, master))
			renderer.fontResolver.addFont(ClassPathResource("/static/font/NanumMyeongjo.ttf").url.toString(),
				BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED
			)
			renderer.fontResolver.addFont(ClassPathResource("/static/font/NanumBarunGothic.ttf").url.toString(),
				BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED
			)
			renderer.fontResolver.addFont(ClassPathResource("/static/font/EastSeaDokdo.ttf").url.toString(),
				BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED
			)
			renderer.fontResolver.addFont(ClassPathResource("/static/font/Hahmlet.ttf").url.toString(),
				BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED
			)
			renderer.fontResolver.addFont(ClassPathResource("/static/font/gulim.ttf").url.toString(),
				BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED
			)
			renderer.layout()
			renderer.createPDF(outputStream)
			outputStream.close()
			val pdf = PdfReader(ByteArrayInputStream(outputStream.toByteArray()))
			readers.add(pdf)

			val executive = mutableListOf<MutableMap<String, Any?>>()
			executives
				?.forEach {
					executive.add(
						mutableMapOf(
							"name" to it.name,
							"position" to it.position,
							"expiredAt" to it.expiredAt
						)
					)
				}
			histories.add(
				DmHistory(
					lawFirmId = company.lawFirmId,
					companyName = company.companyName!!,
					address = company.deliveryPlace.let {
						if (StringUtils.isEmpty(it)) {
							"(${company.companyPostalCode}) ${company.companyAddress}"
						} else {
							"(${company.deliveryPlacePostalCode}) ${company.deliveryPlace}"
						}
					},
					content = jacksonObjectMapper().writeValueAsString(executive)
				)
			)
		}

		dmService.saveHistory(histories)

		val document = Document()
		val out = ByteArrayOutputStream()
		val writer = PdfWriter.getInstance(document, out)
		document.open()
		val cb = writer.directContent
		var page: PdfImportedPage
		var currentPageNumber = 0
		var pageOfCurrentReaderPDF = 0

		val iteratorPDFReader = readers.iterator()

		while (iteratorPDFReader.hasNext()) {

			val pdfReader = iteratorPDFReader.next()
			while (pageOfCurrentReaderPDF < pdfReader.numberOfPages) {
				document.newPage()
				pageOfCurrentReaderPDF++
				currentPageNumber++
				page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF)
				cb.addTemplate(page, 0F, 0F)
			}
			pageOfCurrentReaderPDF = 0
		}
		out.flush()
		document.close();
		out.close()

		return ByteArrayInputStream(out.toByteArray())
	}

	private fun parseThymeleafTemplate(company: Company, executives: List<Executive>?, user: LawFirmUser, templateId: Int, master: List<Executive>?): String {
		val templateResolver = ClassLoaderTemplateResolver()
		templateResolver.suffix = ".html"
		templateResolver.templateMode = TemplateMode.HTML

		val templateEngine = TemplateEngine()
		templateEngine.setTemplateResolver(templateResolver)
		val context = templateId.let {
			if(it == 2) {
				contextGenerator.lawAndOfficeExecutiveExpireDmGenerate(company, executives, user, master)
			} else {
				contextGenerator.defaultExecutiveExpireDmGenerate(company, executives, user, master)
			}
		}
		templateEngine.clearTemplateCache()

		val dm = dmService.details(templateId);

		return templateEngine.process(dm.resourcePath, context)
	}

	private fun getDaysInMonth(month: Int, year: Int): Int {
		return when(month - 1) {
			Calendar.JANUARY, Calendar.MARCH, Calendar.MAY, Calendar.JULY, Calendar.AUGUST, Calendar.OCTOBER, Calendar.DECEMBER -> 31
			Calendar.APRIL, Calendar.JUNE, Calendar.SEPTEMBER, Calendar.NOVEMBER -> 30
			Calendar.FEBRUARY -> if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) 29 else 28
			else -> throw IllegalArgumentException("Invalid Month")
		}
	}

}
