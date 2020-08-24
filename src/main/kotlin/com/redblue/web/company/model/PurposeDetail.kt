package com.redblue.web.company.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "PURPOSE_DETAILS")
data class PurposeDetail(

	@Id
	val id: String,

	val companyId: String,

	val detail: String,

	val detailUpdatedAt: Date,

	val detailRegisterUpdatedAt: Date

)