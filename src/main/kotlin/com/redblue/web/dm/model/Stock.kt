package com.redblue.web.dm.model

import java.util.*

data class Stock(

	val id: String,
	val companyId: String,
	val amount: Int,
	val amountUpdatedAt: Date,
	val amountUpdatedRegisterAt: String,
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
