package com.redblue.web.company

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.company.model.Company
import com.redblue.web.company.model.dto.CompanyListDto
import com.redblue.web.company.service.CompanyService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.time.LocalDate

@Controller
@RequestMapping("/companies")
class CompanyController(
	private val companyService: CompanyService
) {

	@GetMapping
	fun list(
		model: Model,
		@RequestParam(value = "searchValue", required = false) searchValue: String?,
		@RequestParam(value = "start", required = false) startDate: String?,
		@RequestParam(value = "end", required = false) endDate: String?,
		@CurrentUser user: LawFirmUser
	): String {

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

		model.addAttribute("companies", CompanyListDto.to(companyService.list(user.lawFirmId, searchValue), start, end))
		model.addAttribute("totalCount", companyService.count(user.lawFirmId))
		model.addAttribute("q", searchValue)
		model.addAttribute("startDate", startDate)
		model.addAttribute("endDate", endDate)
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