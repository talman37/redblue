package com.redblue.web.company.model

import net.bytebuddy.utility.RandomString
import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

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

	@Temporal(TemporalType.DATE)
	val inauguratedAt: Date? = null,

	val term: Int? = 0,

	val updatedReason: String? = null,

	@Temporal(TemporalType.DATE)
	val updatedAt: Date? = null,

	@Temporal(TemporalType.DATE)
	val expiredAt: Date? = null,

	@Transient
	var expiredAtString: String? = null,

	val stockRatio: Double? = 0.0,

	val stockCount: Int? = 0,

	var nationality: String? = null,

	@Transient
	val countryValue: String? = null

)