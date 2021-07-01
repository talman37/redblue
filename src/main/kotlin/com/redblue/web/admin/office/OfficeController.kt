package com.redblue.web.admin.office

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.admin.office.model.dto.LawFirmCreateRequestDto
import com.redblue.web.admin.office.service.OfficeService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

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

	@GetMapping("/{id}")
	fun details(@CurrentUser user: LawFirmUser, @PathVariable("id") id: String, model: Model): String {
		model.addAttribute("lawFirm", officeService.details(id))
		return "/admin/office/detail"
	}

	@GetMapping("/form")
	fun form(@CurrentUser user: LawFirmUser): String {
		return "/admin/office/form"
	}

	@PostMapping("/form")
	fun save(@RequestBody request: LawFirmCreateRequestDto): String {
		officeService.save(request.to())
		return "redirect: /admin/office"
	}
}