package com.redblue.web.corporation

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/corporation")
class CorporationController {

	@GetMapping
	fun list(): String {
		return "/corporation/list"
	}

	@PostMapping
	fun add() {

	}

}