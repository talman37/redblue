package com.redblue.web.admin.user

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.admin.office.service.OfficeService
import com.redblue.web.admin.user.model.dto.UserCreateRequestDto
import com.redblue.web.admin.user.model.dto.UserUpdateRequestDto
import com.redblue.web.admin.user.service.UserService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/users")
class UserRestController(
	private val service: UserService,
	private val officeService: OfficeService
) {

	@DeleteMapping("/{id}")
	fun delete(
		@PathVariable("id") id: String
	): ResponseEntity<Void> {
		service.delete(id)
		return ResponseEntity(HttpStatus.OK)
	}
}