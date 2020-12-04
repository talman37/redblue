package com.redblue.web.consult

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.consult.model.Consult
import com.redblue.web.consult.service.ConsultService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.text.SimpleDateFormat

@Controller
@RequestMapping("/consult")
class ConsultController(
	private val consultService: ConsultService
) {


	@GetMapping
	fun list(
		@RequestParam(value = "q", required = false) searchValue: String?,
		@RequestParam(value = "start", required = false) startDate: String?,
		@RequestParam(value = "end", required = false) endDate: String?,
		@RequestParam(value = "progress", required = false) progress: List<String>?,
		model: Model,
		@CurrentUser user: LawFirmUser,
		pageable: Pageable
	): String {

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
		var pro = mutableListOf<Consult.Progress>()
		progress?.let { p ->
			p.forEach {
				pro.add(Consult.Progress.valueOf(it.toUpperCase()))
			}
		}

		model.addAttribute("consults", consultService.list(user.lawFirmId, searchValue, start, end, pro, pageable))
		model.addAttribute("q", searchValue)
		model.addAttribute("startDate", startDate)
		model.addAttribute("endDate", endDate)
		model.addAttribute("progress", progress)
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