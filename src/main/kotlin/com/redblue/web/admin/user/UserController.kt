package com.redblue.web.admin.user

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.admin.office.service.OfficeService
import com.redblue.web.admin.user.service.UserService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/users")
class UserController(
	private val service: UserService,
	private val officeService: OfficeService
) {

	@GetMapping
	fun list(@CurrentUser user: LawFirmUser,
	         model: Model
	): String {
		model.addAttribute("users", service.find())
		return "/admin/user/list"
	}

	@GetMapping("/form")
	fun form(@CurrentUser user: LawFirmUser, model: Model): String {
		model.addAttribute("lawFirms", officeService.findAll())
		return "/admin/user/form"
	}

}