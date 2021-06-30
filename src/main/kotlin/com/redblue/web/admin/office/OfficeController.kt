package com.redblue.web.admin.office

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.admin.office.service.OfficeService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/office")
class OfficeController(
	private val officeService: OfficeService
) {

	@GetMapping
	fun list(@CurrentUser user: LawFirmUser, model: Model): String {
		model.addAttribute("lawFirms", officeService.findAll())
		return "/admin/office/list"
	}

	@GetMapping("/form")
	fun form(@CurrentUser user: LawFirmUser): String {
		return "/admin/office/form"
	}
}