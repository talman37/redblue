package com.redblue.web.dm

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/dm")
class DmController {


	@GetMapping
	fun list(): String {
		return "/dm/list"
	}

	@GetMapping("/add")
	fun addForm(): String {
		return "/dm/form"
	}

}