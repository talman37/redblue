package com.redblue.web.company

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.company.model.Company
import com.redblue.web.company.model.Country
import com.redblue.web.company.model.Stock
import com.redblue.web.company.model.dto.CompanyListDto
import com.redblue.web.company.service.CompanyService
import com.redblue.web.consult.service.ConsultService
import com.redblue.web.lawfirm.model.LawFirmUser
import net.bytebuddy.utility.RandomString
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.text.SimpleDateFormat


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
		@RequestParam(value = "state", required = false) state: MutableList<String>?,
		@RequestParam(value = "searchType", required = false) searchType: String?,
		@CurrentUser user: LawFirmUser
	): String {

		model.addAttribute("totalCount", companyService.totalCount(user.lawFirmId))
		model.addAttribute("manageCount", companyService.manageCount(user.lawFirmId))

		val companyState: MutableList<String> = state ?: mutableListOf()

		if(!StringUtils.hasText(searchValue) && !StringUtils.hasText(startDate) && !StringUtils.hasText(endDate) && companyState.isEmpty() && !StringUtils.hasText(searchType)) {
			model.addAttribute("state", mutableListOf("신규법인", "관리법인"))
			return "/company/list"
		}


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

		val companies = companyService.list(user.lawFirmId, searchValue, start, end, companyState, searchType)

		model.addAttribute("companies", CompanyListDto.of(companies))
		model.addAttribute("q", searchValue)
		model.addAttribute("searchType", searchType)
		model.addAttribute("startDate", startDate)
		model.addAttribute("endDate", endDate)
		model.addAttribute("state", companyState)
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

		var name = company.companyName
		if(company.displayCompanyType == Company.DisplayCompanyType.FRONT) {
			name = "(" + company.companyDivision?.first() + ")" + company.companyName
		} else if(company.displayCompanyType == Company.DisplayCompanyType.BACK){
			name = company.companyName + "(" + company.companyDivision?.first() + ")"
		}
		var term = 3
		term = company.executives?.maxBy { it.term!! }!!.term!! ?: 3

		if(company.stock == null) {
			company.stock = Stock(
				id = "SO" + RandomString.make(30),
				companyId = company.id
			)
		}

		var inputStockCount = company.executives?.sumBy { it.stockCount ?: 0 } ?: 0

		model.addAttribute("company", company)
		model.addAttribute("companyName", name)
		model.addAttribute("term", term)
		model.addAttribute("favoriteOffices", user.lawFirmUserRegisterOffice)
		model.addAttribute("consults", consultService.findByCompanyId(id))
		model.addAttribute("countries", Country.values())
		model.addAttribute("inputStockCount", inputStockCount)
		return "/company/detail"
	}

	@GetMapping("/add")
	fun addForm(
		model: Model,
		@CurrentUser user: LawFirmUser
	): String {
		model.addAttribute("favoriteOffices", user.lawFirmUserRegisterOffice)
		model.addAttribute("lawFirmId", user.lawFirmId)
		model.addAttribute("countries", Country.values())
		return "/company/form"
	}

	@GetMapping("/{id}/history")
	fun history(
		@PathVariable("id") id: String,
		model: Model,
		@CurrentUser user: LawFirmUser
	): String {
		model.addAttribute("histories", companyService.getHistories(id))
		return "/popup/company_history"
	}

	@GetMapping("/{id}/consult-add")
	fun addConsult(
		@PathVariable("id") id: String,
		model: Model,
		@CurrentUser user: LawFirmUser
	): String {
		model.addAttribute("companyId", id)
		model.addAttribute("consultant", user.name)
		model.addAttribute("lawFirmId", user.lawFirmId)
		return "/popup/consult_add"
	}

}