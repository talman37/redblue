package com.redblue.web.company.model

import net.bytebuddy.utility.RandomString
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "STOCKS")
data class Stock(

	@Id
	val id: String = "SO" + RandomString.make(30),

	val companyId: String? = null,

	val amount: Long? = 0,

	@Temporal(TemporalType.DATE)
	val amountUpdatedAt: Date? = null,

	val scheduleCount: Long? = 0,

	@Temporal(TemporalType.DATE)
	val scheduleCountUpdatedAt: Date? = null,

	val issuedCount: Long? = 0,

	@Temporal(TemporalType.DATE)
	val issuedCountUpdatedAt: Date? = null,

	val normalCount: Long? = 0,

	val firstCount: Long? = 0,

	val capital: Long? = 0,

	val noFaceValueCount: Long? = 0,

	@Temporal(TemporalType.DATE)
	val noFaceValueUpdatedAt: Date? = null,

	val noFaceValueCapitalAmount: Long? = 0,

	@Temporal(TemporalType.DATE)
	val noFaceValueCapitalAmountUpdatedAt: Date? = null

)
