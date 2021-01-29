package com.redblue.web.lawfirm.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "REGISTER_OFFICES")
data class RegisterOffice(

	@Id
	val id: String,

	val code: String,

	val location: String,

	val name: String

)