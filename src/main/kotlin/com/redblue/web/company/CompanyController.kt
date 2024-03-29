package com.redblue.web.company

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.company.model.Company
import com.redblue.web.company.model.Country
import com.redblue.web.company.model.Stock
import com.redblue.web.company.model.dto.CompanyListDto
import com.redblue.web.company.service.CompanyService
import com.redblue.web.consult.service.ConsultService
import com.redblue.web.dm.service.DmService
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
import java.util.*


@Controller
@RequestMapping("/company")
class CompanyController(
	private val companyService: CompanyService,
	private val consultService: ConsultService,
	private val dmService: DmService
) {

	@GetMapping
	fun list(
		model: Model,
		@RequestParam(value = "q", required = false) searchValue: String?,
		@RequestParam(value = "start", required = false) startDate: String?,
		@RequestParam(value = "end", required = false) endDate: String?,
		@RequestParam(value = "state", required = false) state: MutableList<String>?,
		@RequestParam(value = "searchType", required = false) searchType: String?,
		@RequestParam(value = "positionTarget", required = false) positionTarget: String?,
		@RequestParam(value = "modifiedStartDate", required = false) modifiedStartDate: String?,
		@RequestParam(value = "modifiedEndDate", required = false) modifiedEndDate: String?,
		@RequestParam(value = "searchRange", required = false) searchRange: String?,
		@RequestParam(value = "registerOffice", required = false) registerOffice: String?,
		@CurrentUser user: LawFirmUser
	): String {

		model.addAttribute("totalCount", companyService.totalCount(user.lawFirmId))
		model.addAttribute("manageCount", companyService.manageCount(user.lawFirmId))
		model.addAttribute("dmList", dmService.findByLawFirmId(user.lawFirmId))

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

		var start = startDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(startDate)
			}
		}

		var end = endDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(endDate)
			}
		}

		val updatedStart = modifiedStartDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(modifiedStartDate)
			}
		}

		val updatedEnd = modifiedEndDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(modifiedEndDate)
			}
		}

		val companies = companyService.list(user.lawFirmId, searchValue, start, end, companyState, searchType, positionTarget, updatedStart, updatedEnd, searchRange, registerOffice)

		model.addAttribute("companies", CompanyListDto.of(companies))
		model.addAttribute("q", searchValue)
		model.addAttribute("searchType", searchType)
		model.addAttribute("startDate", startDate)
		model.addAttribute("endDate", endDate)
		model.addAttribute("state", companyState)
		model.addAttribute("positionTarget", positionTarget)
		model.addAttribute("modifiedStartDate", modifiedStartDate)
		model.addAttribute("modifiedEndDate", modifiedEndDate)
		model.addAttribute("name", user.name)
		model.addAttribute("searchRange", searchRange)
		model.addAttribute("registerOffice", registerOffice)

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
		if(!company.executives.isNullOrEmpty()) {
			term = company.executives!!.maxBy { it.term!! }!!.term!!
		}

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
		model.addAttribute("lawFirmUserId", user.id)
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

	@GetMapping("/{id}/detail-popup")
	fun detailPopup(
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
		if(!company.executives.isNullOrEmpty()) {
			term = company.executives!!.maxBy { it.term!! }!!.term!!
		}

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
		model.addAttribute("lawFirmUserId", user.id)

		return "/popup/company_detail"
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
		val executive = companyService.findExecutivesByCompanyId(id)
			.first()
		model.addAttribute("expiredAt", executive.expiredAt ?: "")

		return "/popup/consult_add"
	}

}