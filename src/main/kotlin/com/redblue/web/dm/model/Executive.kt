package com.redblue.web.dm.model

import java.util.*

data class Executive(

	val id: String,
	val companyId: String,
	val detail: String,
	val type: String,
	val name: String,
	val registrationNumber1: String,
	val registrationNumber2: String,
	val address: String,
	val position: String,
	val updatedReason: String,
	val updatedAt: Date,
	val registerUpdatedAt: Date,
	val expiredAt: Date

)