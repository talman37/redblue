package com.redblue.web.user.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "LAW_FIRMS")
data class LawFirm(

	@Id
	val id: String,

	val name: String,

	val description: String,

	val createdAt: Date

)