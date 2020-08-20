package com.redblue.web.dm.model

import java.util.*

data class Company(

	val id: String,

	val lawFirmId: String,

	val registerOffice: String,

	val registerNumber: Int,

	val companyNumber1: Int,

	val companyNumber2: Int,

	val companyName: String,

	val companyUpdatedAt: Date,

	val companyAddress: String,

	val companyAddressUpdatedAt: Date,

	val companyAddressRegisterUpdatedAt: Date,

	val businessType: String,

	val businessCondition: String,

	val deliveryPlace: String
	,
	val companyName2: String,

	val etc: String,

	val convertibleBond: String,

	val stockPurchaseOption: String,

	val companyFormationAt: Date,

	val registerRecordCreateReason: String,

	val registerRecordCreateAt: Date,

	val isHeadOfficeTransfer: String,

	val headOfficeTransferAt: Date,

	val headOfficeTransferRegisterAt: Date,

	val isDisband: Boolean,

	val disbandAt: Date,

	val disbandRegisterAt: Date,

	val disbandDeemedAt: Date,

	val isLiquidation: Boolean,

	val liquidationAt: Date,

	val liquidationRegisterAt: Date,

	val isRegisterRecordClosure: String,

	val registerRecordClosureAt: Date,

	val settlementAt: Date

)