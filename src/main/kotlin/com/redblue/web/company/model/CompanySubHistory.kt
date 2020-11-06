package com.redblue.web.company.model

import net.bytebuddy.utility.RandomString
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "COMPANY_SUB_HISTORY")
data class CompanySubHistory(

	@Id
	val id: String? = "CMSH" + RandomString.make(28),

	@Enumerated(EnumType.STRING)
	val type: IssuedType? = null,

	val companyId: String? = null,

	val companyAddress: String? = null,

	val companyPostalCode: String? = null,

	val deliveryPlace: String? = null,

	val deliveryPlacePostalCode: String? = null,

	val businessNumber: String? = null,

	val businessType: String? = null,

	val businessCondition: String? = null,

	val settlementMonth: Int? = null,

	@Temporal(TemporalType.DATE)
	val issuedAt: Date? = Date()

)