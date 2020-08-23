package com.redblue.web.company

import com.redblue.web.company.service.CompanyService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/company")
class CompanyController(
	private val companyService: CompanyService
) {

	@GetMapping
	fun list(model: Model): String {
		model.addAttribute("companies", companyService.list())
		return "/company/list"
	}

	@PostMapping
	fun add() {

	}

}