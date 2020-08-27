package com.redblue.web.company

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.security.core.userdetails.SecurityUser
import com.redblue.web.company.service.CompanyService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
	fun list(
		model: Model,
		@CurrentUser user: LawFirmUser
	): String {
		model.addAttribute("companies", companyService.list(user.lawFirmId))
		return "/company/list"
	}

	@PostMapping
	fun add() {

	}

}