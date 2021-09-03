package com.redblue.web.lawfirm

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.lawfirm.model.LawFirmUser
import com.redblue.web.lawfirm.model.dto.LawFirmUserUpdateDto
import com.redblue.web.lawfirm.service.LawFirmService
import com.redblue.web.lawfirm.service.LawFirmUserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/me")
class MeController(
	private val lawFirmUserService: LawFirmUserService,
	private val lawFirmService: LawFirmService
) {

	@GetMapping
	fun me(
		@CurrentUser user: LawFirmUser,
		model: Model
	) : String {
		model.addAttribute("user", lawFirmUserService.details(user.id))
		model.addAttribute("userRegisterOffices", lawFirmService.findUserRegisterOffice(user.id))
		return "/me/detail"
	}

	@PostMapping
	fun update(
		@RequestBody lawFirmUserUpdateDto: LawFirmUserUpdateDto
	): String {
		lawFirmUserService.update(lawFirmUserUpdateDto);
		return "redirect:/me"
	}

}