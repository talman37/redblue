package com.redblue.web.company

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.company.model.dto.CompanyListDto
import com.redblue.web.company.service.CompanyService
import com.redblue.web.consult.service.ConsultService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.text.SimpleDateFormat
import java.util.stream.Collectors
import java.util.stream.IntStream


@Controller
@RequestMapping("/company")
class CompanyController(
	private val companyService: CompanyService,
	private val consultService: ConsultService
) {

	@GetMapping
	fun list(
		model: Model,
		@RequestParam(value = "q", required = false) searchValue: String?,
		@RequestParam(value = "start", required = false) startDate: String?,
		@RequestParam(value = "end", required = false) endDate: String?,
		@CurrentUser user: LawFirmUser,
		pageable: Pageable
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

		val companies = companyService.list(user.lawFirmId, searchValue, start, end, pageable)

		model.addAttribute("companies", CompanyListDto.to(companies, start, end))
		model.addAttribute("companiesPage", companies)
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
		model: Model,
		@CurrentUser user: LawFirmUser
	): String {
		val company = companyService.detail(id)
		model.addAttribute("company", company)
		model.addAttribute("favoriteOffices", user.lawFirmUserRegisterOffice)
		model.addAttribute("consults", consultService.findByCompanyId(id))
		return "/company/detail"
	}

	@GetMapping("/add")
	fun addForm(
		model: Model,
		@CurrentUser user: LawFirmUser
	): String {
		model.addAttribute("favoriteOffices", user.lawFirmUserRegisterOffice)
		model.addAttribute("lawFirmId", user.lawFirmId)
		return "/company/form"
	}


}