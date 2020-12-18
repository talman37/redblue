package com.redblue.web.company.service

import com.lowagie.text.Document
import com.lowagie.text.pdf.BaseFont
import com.lowagie.text.pdf.PdfImportedPage
import com.lowagie.text.pdf.PdfReader
import com.lowagie.text.pdf.PdfWriter
import com.redblue.web.company.model.Company
import com.redblue.web.company.model.Executive
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import org.xhtmlrenderer.pdf.ITextRenderer
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


@Service
class CompanyDmService {

	fun generate(companies: List<Company>): ByteArrayInputStream {

		val readers = mutableListOf<PdfReader>()
		for (company in companies) {
			val outputStream = ByteArrayOutputStream()
			val renderer = ITextRenderer()
			renderer.fontResolver.addFont(ClassPathResource("/static/font/NanumMyeongjo-Regular.ttf").url.toString(),
					BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED
			)
			renderer.fontResolver.addFont(ClassPathResource("/static/font/NanumBarunGothic.ttf").url.toString(),
				BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED
			)
			renderer.setDocumentFromString(this.parseThymeleafTemplate(company.executives))
			renderer.layout()
			renderer.createPDF(outputStream)
			outputStream.close()
			val pdf = PdfReader(ByteArrayInputStream(outputStream.toByteArray()))
			readers.add(pdf)
		}

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

	private fun parseThymeleafTemplate(executives: List<Executive>): String {
		val templateResolver = ClassLoaderTemplateResolver()
		templateResolver.suffix = ".html";
		templateResolver.templateMode = TemplateMode.HTML;

		val templateEngine = TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver)

		val context = Context()
		context.setVariable("to", "Baeldung")
		context.setVariable("executives", executives)
		templateEngine.clearTemplateCache()
		return templateEngine.process("templates/dm/dm", context)
	}

}