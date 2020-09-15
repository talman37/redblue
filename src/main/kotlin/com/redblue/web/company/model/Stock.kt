package com.redblue.web.company.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "STOCKS")
data class Stock(

	@Id
	val id: String,

	val companyId: String? = null,

	val amount: Int? = 0,

	@Temporal(TemporalType.DATE)
	val amountUpdatedAt: Date? = null,

	@Temporal(TemporalType.DATE)
	val amountUpdatedRegisterAt: Date? = null,

	val scheduleCount: Int? = 0,

	@Temporal(TemporalType.DATE)
	val scheduleCountUpdatedAt: Date? = null,

	@Temporal(TemporalType.DATE)
	val scheduleCountUpdatedRegisterAt: Date? = null,

	val issuedCount: Int? = 0,

	@Temporal(TemporalType.DATE)
	val issuedCountUpdatedAt: Date? = null,

	@Temporal(TemporalType.DATE)
	val issuedCountUpdatedRegisterAt: Date? = null,

	val normalCount: Int? = 0,

	val firstCount: Int? = 0,

	val noFaceValueCount: Int? = 0,

	@Temporal(TemporalType.DATE)
	val noFaceValueUpdatedAt: Date? = null,

	@Temporal(TemporalType.DATE)
	val noFaceValueUpdatedRegisterAt: Date? = null,

	val noFaceValueCapitalAmount: Int? = 0,

	@Temporal(TemporalType.DATE)
	val noFaceValueCapitalAmountUpdatedAt: Date? = null,

	@Temporal(TemporalType.DATE)
	val noFaceValueCapitalAmountUpdatedRegisterAt: Date? = null

)
