package com.redblue.web.admin.user

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.admin.office.service.OfficeService
import com.redblue.web.admin.user.model.dto.UserCreateRequestDto
import com.redblue.web.admin.user.model.dto.UserUpdateRequestDto
import com.redblue.web.admin.user.service.UserService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

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

	@GetMapping("/{id}")
	fun details(@CurrentUser user: LawFirmUser,
	            @PathVariable("id") id: String,
	         model: Model
	): String {
		model.addAttribute("lawFirms", officeService.findAll())
		model.addAttribute("user", service.detail(id))
		return "/admin/user/detail"
	}

	@GetMapping("/form")
	fun form(@CurrentUser user: LawFirmUser, model: Model): String {
		model.addAttribute("lawFirms", officeService.findAll())
		return "/admin/user/form"
	}

	@PostMapping("/{id}")
	fun update(@CurrentUser user: LawFirmUser,
	           @ModelAttribute requestBody: UserUpdateRequestDto): String {
		service.update(requestBody)
		return "redirect:/admin/users"
	}

	@PostMapping("/form")
	fun save(@CurrentUser user: LawFirmUser, @ModelAttribute requestBody: UserCreateRequestDto): String {
		service.save(requestBody.to())
		return "redirect:/admin/users"
	}

}