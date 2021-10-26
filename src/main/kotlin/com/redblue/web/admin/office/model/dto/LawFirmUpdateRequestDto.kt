package com.redblue.web.admin.office.model.dto

data class LawFirmUpdateRequestDto(

	val id: String,

	val name: String,

	val address: String,

	val postalCode: String,

	val tel: String,

	val fax: String,

	val description: String?,

	val etc: String?,

	val representative: String

)
