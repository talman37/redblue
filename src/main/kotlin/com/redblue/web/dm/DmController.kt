package com.redblue.web.dm

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.dm.service.DmService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.text.SimpleDateFormat

@Controller
@RequestMapping("/dm")
class DmController(
	private val dmService: DmService
) {


	@GetMapping
	fun list(
		@RequestParam(value = "q", required = false) searchValue: String?,
		@RequestParam(value = "start", required = false) startDate: String?,
		@RequestParam(value = "end", required = false) endDate: String?,
		@CurrentUser user: LawFirmUser,
		model: Model
	): String {

		val searchValue = searchValue?.let {
			if (it.isEmpty()) {
				null
			} else {
				searchValue
			}
		}

		val start = startDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(startDate)
			}
		}

		val end = endDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(endDate)
			}
		}

		model.addAttribute("histories", dmService.list(user.lawFirmId, searchValue, start, end))
		model.addAttribute("q", searchValue)
		model.addAttribute("startDate", startDate)
		model.addAttribute("endDate", endDate)
		return "/dm/list"
	}

	@GetMapping("/add")
	fun addForm(): String {
		return "/dm/form"
	}

}