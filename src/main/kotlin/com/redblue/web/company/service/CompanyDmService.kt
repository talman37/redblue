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
			}
			val master = company.executives?.filter {
				it.expiredAt != null && (it.position.equals("대표이사") || it.position.equals("사내이사"))
			}
			renderer.setDocumentFromString(this.parseThymeleafTemplate(company, executives, user, templateId, master))
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

		val templateEngine = TemplateEngine();
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

}