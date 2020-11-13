package com.redblue.web.company.service

import com.lowagie.text.pdf.BaseFont
import com.redblue.web.company.model.Company
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
		val outputStream = ByteArrayOutputStream()
		val renderer = ITextRenderer()
		renderer.fontResolver.addFont(ClassPathResource("/static/font/NanumBarunGothic.ttf").url.toString(),
			BaseFont.IDENTITY_H,
			BaseFont.EMBEDDED
		)
		renderer.setDocumentFromString(this.parseThymeleafTemplate())
		renderer.layout()
		renderer.createPDF(outputStream)
		outputStream.close()
		return ByteArrayInputStream(outputStream.toByteArray())
	}

	private fun parseThymeleafTemplate(): String {
		val templateResolver = ClassLoaderTemplateResolver()
		templateResolver.suffix = ".html";
		templateResolver.templateMode = TemplateMode.HTML;

		val templateEngine = TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver)

		val context = Context()
		context.setVariable("to", "Baeldung")
		templateEngine.clearTemplateCache()
		return templateEngine.process("templates/dm/dm", context)
	}

}