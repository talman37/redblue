package com.redblue.web.lawfirm.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "LAW_FIRMS")
data class LawFirm(

	@Id
	val id: String,

	val name: String,

	val address: String,

	val postal_code: String,

	val tel: String,

	val fax: String,

	val description: String,

	val etc: String,

	@Temporal(TemporalType.DATE)
	val createdAt: Date

)