package com.redblue.web.lawfirm.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "LAW_FIRMS")
data class LawFirm(

	@Id
	val id: String,

	var name: String,

	var address: String,

	var postalCode: String,

	var tel: String,

	var fax: String?,

	var description: String?,

	var etc: String?,

	@Temporal(TemporalType.DATE)
	val createdAt: Date,

	var representative: String

)