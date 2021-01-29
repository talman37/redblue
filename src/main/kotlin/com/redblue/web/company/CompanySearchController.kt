package com.redblue.web.company

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.company.model.Company
import com.redblue.web.company.service.CompanyService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/company_search")
class CompanySearchController(
	private val companyService: CompanyService
) {

	@GetMapping
	fun search(
		@RequestParam(value = "name", required = false) name: String?,
		model: Model,
		@CurrentUser user: LawFirmUser
	): String {

		if(StringUtils.isEmpty(name)) {
			model.addAttribute("name", "")
			model.addAttribute("companies", mutableListOf<Company>())
		} else {
			model.addAttribute("name", name)
			model.addAttribute("companies", companyService.findByName(user.lawFirmId, name!!))
		}
		return "/popup/company_search"
	}

}