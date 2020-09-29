package com.redblue.web.company.model

import net.bytebuddy.utility.RandomString
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "EXECUTIVES")
data class Executive(

	@Id
	val id: String = "EC" + RandomString.make(30),

	var companyId: String? = null,

	val detail: String? = null,

	val type: String? = null,

	val name: String? = null,

	val registrationNumber1: String? = null,

	val registrationNumber2: String? = null,

	val address: String? = null,

	val position: String? = null,

	val updatedReason: String? = null,

	@Temporal(TemporalType.DATE)
	val updatedAt: Date? = null,

	@Temporal(TemporalType.DATE)
	val expiredAt: Date? = null,

	val stockCount: Int? = 0

)