package com.redblue.web.company

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.company.service.CompanyService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/companies")
class CompanyRestController(
	private val companyService: CompanyService
) {

	@GetMapping
	fun search(
		@RequestParam("q") q: String,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<String> {
		val company = companyService.search(user.lawFirmId, q)
		return ResponseEntity.ok("/company/${company.id}")
	}

}