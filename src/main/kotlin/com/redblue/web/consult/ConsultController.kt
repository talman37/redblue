package com.redblue.web.consult

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.consult.service.ConsultService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.data.domain.Pageable
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
		@CurrentUser user: LawFirmUser,
		pageable: Pageable
	): String {
		model.addAttribute("consults", consultService.list(user.lawFirmId, pageable))
		return "/consult/list"
	}

	@GetMapping("/add")
	fun addForm(
		model: Model,
		@CurrentUser user: LawFirmUser
	): String {
		model.addAttribute("consultant", user.name)
		return "/consult/form"
	}

}