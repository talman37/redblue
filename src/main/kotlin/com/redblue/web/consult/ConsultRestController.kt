package com.redblue.web.consult

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.consult.model.Consult
import com.redblue.web.consult.service.ConsultService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1/consults")
class ConsultRestController(
	private val consultService: ConsultService
) {

	@PatchMapping("/{id}")
	fun updateConsult(
		@PathVariable("id") id: String,
		@RequestBody consult: Consult,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<Void> {
		consultService.update(consult)
		return ResponseEntity(HttpStatus.OK)
	}

}