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

	val amount: Int? = 0,

	@Temporal(TemporalType.DATE)
	val amountUpdatedAt: Date? = null,

	val scheduleCount: Int? = 0,

	@Temporal(TemporalType.DATE)
	val scheduleCountUpdatedAt: Date? = null,

	val issuedCount: Int? = 0,

	@Temporal(TemporalType.DATE)
	val issuedCountUpdatedAt: Date? = null,

	val normalCount: Int? = 0,

	val firstCount: Int? = 0,

	val noFaceValueCount: Int? = 0,

	@Temporal(TemporalType.DATE)
	val noFaceValueUpdatedAt: Date? = null,

	val noFaceValueCapitalAmount: Int? = 0,

	@Temporal(TemporalType.DATE)
	val noFaceValueCapitalAmountUpdatedAt: Date? = null

)
