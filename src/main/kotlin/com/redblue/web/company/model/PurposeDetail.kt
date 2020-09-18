package com.redblue.web.company.model

import net.bytebuddy.utility.RandomString
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "PURPOSE_DETAILS")
data class PurposeDetail(

	@Id
	val id: String = "PD" + RandomString.make(30),

	val companyId: String,

	val detail: String? = null,

	@Temporal(TemporalType.DATE)
	val detailUpdatedAt: Date? = null,

	@Temporal(TemporalType.DATE)
	val detailRegisterUpdatedAt: Date? = null

)