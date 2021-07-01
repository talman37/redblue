package com.redblue.web.company.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.lowagie.text.Document
import com.lowagie.text.pdf.BaseFont
import com.lowagie.text.pdf.PdfImportedPage
import com.lowagie.text.pdf.PdfReader
import com.lowagie.text.pdf.PdfWriter
import com.redblue.web.company.model.Company
import com.redblue.web.company.model.Executive
import com.redblue.web.dm.model.DmHistory
import com.redblue.web.dm.service.DmService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.core.io.FileSystemResource
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import org.xhtmlrenderer.pdf.ITextRenderer
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*


@Service
class CompanyDmService(
	private val dmService: DmService,
	private val companyService: CompanyService
) {

	fun generate(companies: List<Company>, user: LawFirmUser): ByteArrayInputStream {

		val readers = mutableListOf<PdfReader>()
		val histories = mutableListOf<DmHistory>()
		for (company in companies) {
			val outputStream = ByteArrayOutputStream()
			val renderer = ITextRenderer()
			renderer.fontResolver.addFont("static/font/NanumMyeongjo-Regular.ttf",
				BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED
			)
			renderer.fontResolver.addFont("static/font/NanumBarunGothic.ttf",
				BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED
			)
			val executives = companyService.findExecutivesByCompanyId(company.id)
			renderer.setDocumentFromString(this.parseThymeleafTemplate(company, executives, user))
			renderer.layout()
			renderer.createPDF(outputStream)
			outputStream.close()
			val pdf = PdfReader(ByteArrayInputStream(outputStream.toByteArray()))
			readers.add(pdf)

			val executive = mutableListOf<MutableMap<String, Any?>>()
			executives
				.forEach {
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
					address = company.deliveryPlacePostalCode.let {
						if (StringUtils.isEmpty(it)) {
							company.deliveryPlace!!
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

	private fun parseThymeleafTemplate(company: Company, executives: List<Executive>, user: LawFirmUser): String {
		val templateResolver = ClassLoaderTemplateResolver()
		templateResolver.suffix = ".html"
		templateResolver.templateMode = TemplateMode.HTML

		val templateEngine = TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver)


		val context = Context().apply {
			this.setVariable("lawFirm", user.lawFirm)
			this.setVariable("tel", "(대표), ${user.lawFirm!!.tel}")
			this.setVariable("postalCode", "<span>우)</span> <strong>${user.lawFirm!!.postalCode}</strong>")
			this.setVariable("docNum", "<span>문서번호 :</span> <strong>${company.companyNumber1} <span>[${company.companyNumber2}]</span></strong>")
			this.setVariable("exAddress", company.deliveryPlace)

			var companyNamePrefix = ""
			if(StringUtils.hasText(company.companyDivision)) {
				companyNamePrefix = "(" + company.companyDivision!!.slice(IntRange(0, 0)) + ")"
			}
			when (company.displayCompanyType) {
				Company.DisplayCompanyType.FRONT -> {
					this.setVariable("exCompany", companyNamePrefix + company.companyName)
				}
				Company.DisplayCompanyType.BACK -> {
					this.setVariable("exCompany", company.companyName + companyNamePrefix)
				}
				else -> {
					this.setVariable("exCompany", company.companyName)
				}
			}

			var masterInfo = executives.filter {
				it.position!!.contains( "대표이사")
			}

			if(masterInfo.isEmpty()) {
				masterInfo = executives.filter {
					it.position!!.contains( "사내이사")
				}
			}

			this.setVariable("exName", "대표이사 <strong>${masterInfo[0].name}</strong>님 귀하")
			this.setVariable("exPost", "16898")
			this.setVariable("conTitle", "제목 : <strong>임기만료 안내문</strong>")
			var content0 = "귀사의 무궁한 발전을 기원합니다."
			var content1 = "임기만료안내"
			var content2 = "<div>" +
				"<p>안녕하십니까. <span>${user.lawFirm!!.representative}</span>법무사입니다.</p>" +
				"<p><strong>이번에 아래와 같이 귀사의 임원임기가 만료되었음을 안내해 드리오니, 연락주시면 귀사를 직접 방문하여 성심성의껏 업무처리를 도와드리겠습니다.</strong></p>" +
				"</div>"
			this.setVariable("content0", content0)
			this.setVariable("content1", content1)
			this.setVariable("content2", content2)
			this.setVariable("hr", "아래")
			this.setVariable("tableTitle1", "직위")
			this.setVariable("tableTitle2", "성명")
			this.setVariable("tableTitle3", "만기일자")
			this.setVariable("executives", executives)
			this.setVariable("etc", "참고사항")
			this.setVariable("etcContent0", "법정기간(2주)내에 임원변경등기를 하지 않으면 500만원 이하의 과태료가 부과될 수 있음을 각별히 유의하시기 바랍니다.")
			this.setVariable("etcContent1", "위 정보는 일반에게 공개되는 법인등기부에 의해 확인된 사실을 기초로 작성된 것이므로, 개인정보침해는 전혀 없음을 밝혀 드립니다.")
			this.setVariable("etcContent2", "대표이사(1인 이사인 경우는 이사)의 경우는 그 주소가 변경된 경우에도 2주이내에 변경등기하셔야 과태료부과를 피하실 수 있음을 특별히 기억하시기 바랍니다.")
			this.setVariable("desc", "\"법인등기 이외에도 귀사의 채권채무문제, 가압류 가처분 및 부동산취득 등 각종 법률문제에 대해서도 문의하시면 정성과 책임을 다해 상담하여 드리겠습니다.\"")

		}

		templateEngine.clearTemplateCache()
		return templateEngine.process("templates/dm/dm", context)
	}

}