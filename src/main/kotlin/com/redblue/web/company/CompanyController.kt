package com.redblue.web.company

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.security.core.userdetails.SecurityUser
import com.redblue.web.company.model.Company
import com.redblue.web.company.model.dto.CompanyListDto
import com.redblue.web.company.service.CompanyService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/company")
class CompanyController(
	private val companyService: CompanyService
) {

	@GetMapping
	fun list(
		model: Model,
		@CurrentUser user: LawFirmUser
	): String {
		model.addAttribute("companies", CompanyListDto.to(companyService.list(user.lawFirmId)))
		model.addAttribute("totalCount", companyService.count(user.lawFirmId))
		model.addAttribute("name", user.name)
		return "/company/list"
	}

	@GetMapping("/{id}")
	fun detail(
		@PathVariable("id") id: String,
		model: Model
	): String {
		model.addAttribute("company", companyService.detail(id))
		return "/company/detail"
	}

	@GetMapping("/add")
	fun addForm(): String {
		return "/company/form"
	}

	@PostMapping("/add")
	fun add(
		@RequestBody company: Company
	) {
		companyService.save(company)
	}

}