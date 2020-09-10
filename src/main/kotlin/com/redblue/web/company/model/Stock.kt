package com.redblue.web.company.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "STOCKS")
data class Stock(

	@Id
	val id: String,

	val companyId: String? = null,

	val amount: Int,

	val amountUpdatedAt: Date,

	val amountUpdatedRegisterAt: Date,

	val scheduleCount: Int,

	val scheduleCountUpdatedAt: Date,

	val scheduleCountUpdatedRegisterAt: Date,

	val issuedCount: Int,

	val issuedCountUpdatedAt: Date,

	val issuedCountUpdatedRegisterAt: Date,

	val normalCount: Int,

	val firstCount: Int,

	val noFaceValueCount: Int,

	val noFaceValueUpdatedAt: Date,

	val noFaceValueUpdatedRegisterAt: Date,

	val noFaceValueCapitalAmount: Int,

	val noFaceValueCapitalAmountUpdatedAt: Date,

	val noFaceValueCapitalAmountUpdatedRegisterAt: Date

)
