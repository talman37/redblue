package com.redblue.web.admin.user.model.dto

data class UserUpdateRequestDto(

	val id: String,

	val lawFirmId: String,

	val email: String,

	val password: String?,

	val name: String

)