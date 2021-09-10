package com.redblue.web.lawfirm

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.lawfirm.model.LawFirmUser
import com.redblue.web.lawfirm.model.dto.LawFirmUserUpdateDto
import com.redblue.web.lawfirm.service.LawFirmUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/me")
class MeRestController (
	private val lawFirmUserService: LawFirmUserService
){
	@PostMapping
	fun update(
		@RequestBody lawFirmUserUpdateDto: LawFirmUserUpdateDto
	): ResponseEntity<Void> {
		lawFirmUserService.update(lawFirmUserUpdateDto)
		return ResponseEntity(HttpStatus.OK)
	}

	@DeleteMapping("/offices/{officeId}")
	fun officeDelete(
		@CurrentUser user: LawFirmUser,
		@PathVariable("officeId") officeId: String
	): ResponseEntity<Void> {
		lawFirmUserService.deleteOffice(user.id, officeId)
		return ResponseEntity(HttpStatus.OK)
	}

}