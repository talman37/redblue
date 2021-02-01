package com.redblue.web.company.model

import net.bytebuddy.utility.RandomString
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "PURPOSE_DETAILS")
data class PurposeDetail(

	@Id
	val id: String = "PD" + RandomString.make(30),

	var companyId: String? = null,

	var ordinal: Int? = null,

	val detail: String? = null,

	@Temporal(TemporalType.DATE)
	var detailUpdatedAt: Date = Date()

)