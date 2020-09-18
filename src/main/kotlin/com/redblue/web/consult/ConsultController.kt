package com.redblue.web.consult

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.consult.service.ConsultService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/consult")
class ConsultController(
	private val consultService: ConsultService
) {


	@GetMapping
	fun list(
		model: Model,
		@CurrentUser user: LawFirmUser
	): String {
		model.addAttribute("consults", consultService.list(user.lawFirmId))
		return "/consult/list"
	}

	@GetMapping("/add")
	fun addForm(): String {
		return "/consult/form"
	}

}