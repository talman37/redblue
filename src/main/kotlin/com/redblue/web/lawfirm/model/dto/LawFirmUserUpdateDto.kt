package com.redblue.web.lawfirm.model.dto

data class LawFirmUserUpdateDto(

	val id: String,

	val email: String,

	val name: String,

	val password: String?,

	val lawFirmUserRegisterOffices: List<lawFirmUserRegisterOfficeUpdateDto> = emptyList()

)
