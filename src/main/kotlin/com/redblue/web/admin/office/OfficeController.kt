package com.redblue.web.admin.office

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/offices")
class OfficeController {

	@GetMapping
	fun list(@CurrentUser user: LawFirmUser): String {
		return "/admin/office/list"
	}

	@GetMapping("/form")
	fun form(@CurrentUser user: LawFirmUser): String {
		return "/admin/office/form"
	}
}