package com.redblue.web.consult

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/consult")
class ConsultController {


	@GetMapping
	fun list(): String {
		return "/consult/list"
	}

	@GetMapping("/add")
	fun addForm(): String {
		return "/consult/form"
	}

}