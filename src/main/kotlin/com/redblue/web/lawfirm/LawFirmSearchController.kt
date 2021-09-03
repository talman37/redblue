package com.redblue.web.lawfirm

import com.redblue.web.lawfirm.service.LawFirmService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class LawFirmSearchController(
	private val lawFirmService: LawFirmService
) {

	@GetMapping("/office_search")
	fun findRegisterOffice(
		@RequestParam("q", required = false) searchValue: String?,
		@RequestParam("projection", required = false) projection: String?,
		model: Model
	): String {
		model.addAttribute("q", searchValue)
		model.addAttribute("offices", lawFirmService.findRegisterOffice(searchValue))
		if("me" == projection) {
			return "/popup/office_search_me"
		}
		return "/popup/office_search"
	}

}