package com.redblue.web.admin.dm

import com.redblue.web.admin.dm.service.DmManageService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/dm")
class DmManageController(
	private val dmManageService: DmManageService
) {


	@GetMapping
	fun list(model: Model): String {
		model.addAttribute("dmList", dmManageService.list())
		return "/admin/dm/list"
	}

}