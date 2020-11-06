package com.redblue.web.dashboard

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class DashBoardController {

	@GetMapping()
	fun dashBoard() :String {
		return "redirect:/company"
	}

}