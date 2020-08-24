package com.redblue.web.company.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "EXECUTIVES")
data class Executive(

	@Id
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