package com.redblue.web.company.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "COMPANY")
data class Company(

	@Id
	val id: String,

	val lawFirmId: String,

	val registerOffice: String,

	val registerNumber: Int,

	val companyNumber1: Int,

	val companyNumber2: Int,

	val companyName: String,

	val companySubName: String,

	val companyUpdatedAt: Date,

	val companyAddress: String,

	val companyAddressUpdatedAt: Date,

	val companyAddressRegisterUpdatedAt: Date,

	val businessType: String,

	val businessCondition: String,

	val deliveryPlace: String,

	val etc: String,

	val convertibleBond: String,

	val stockPurchaseOption: String,

	val companyFormationAt: Date,

	val registerRecordCreateReason: String,

	val registerRecordCreateAt: Date,

	val isHeadOfficeTransfer: Boolean,

	val headOfficeTransferAt: Date,

	val headOfficeTransferRegisterAt: Date,

	val isDisband: Boolean,

	val disbandAt: Date,

	val disbandRegisterAt: Date,

	val disbandDeemedAt: Date,

	val isLiquidation: Boolean,

	val liquidationAt: Date,

	val liquidationRegisterAt: Date,

	val isRegisterRecordClosure: Boolean,

	val registerRecordClosureAt: Date,

	val settlementAt: Date

)