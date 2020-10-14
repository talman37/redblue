package com.redblue.web.company

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.company.model.dto.CompanyCreateDto
import com.redblue.web.company.service.CompanyExcelService
import com.redblue.web.company.service.CompanyService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat

@RestController
@RequestMapping("/v1/companies")
class CompanyRestController(
	private val companyService: CompanyService,
	private val companyExcelService: CompanyExcelService
) {

	@GetMapping
	fun search(
		@RequestParam("q") q: String,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<String> {
		val company = companyService.search(user.lawFirmId, q)
		return ResponseEntity.ok("/company/${company.id}")
	}

	@PostMapping
	fun save(
		@RequestBody dto: CompanyCreateDto
	): ResponseEntity<Void> {
		val dtos = dto
		return ResponseEntity(HttpStatus.CREATED)
	}

	@GetMapping("/download/corporations.xlsx")
	fun downloadExcel(
		@RequestParam(value = "searchValue", required = false) searchValue: String?,
		@RequestParam(value = "start", required = false) startDate: String?,
		@RequestParam(value = "end", required = false) endDate: String?,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<InputStreamResource> {

		val searchValue = searchValue?.let {
			if (it.isEmpty()) {
				null
			} else {
				searchValue
			}
		}

		val start = startDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(startDate)
			}
		}

		val end = endDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(endDate)
			}
		}

		val companies = companyService.list(user.lawFirmId, searchValue)
		val resource = companyExcelService.generate(companies)
		val headers = HttpHeaders()
		headers.add("Content-Disposition", "attachment; filename=corporations.xlsx")

		return ResponseEntity.ok().headers(headers).body(InputStreamResource(resource))

	}

}