package com.redblue.web.admin.user

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.admin.user.service.UserService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/users")
class UserController(
	private val service: UserService
) {


	@GetMapping
	fun list(@CurrentUser user: LawFirmUser,
	         model: Model
	): String {
		model.addAttribute("users", service.find())
		return "/admin/user/list"
	}

	@GetMapping("/form")
	fun form(@CurrentUser user: LawFirmUser): String {
		return "/admin/user/form"
	}

}