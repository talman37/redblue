package com.redblue.web.company.model

import net.bytebuddy.utility.RandomString
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "COMPANIES")
data class Company(

	@Id
	val id: String = "CM" + RandomString.make(30),

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

	val companyFormationAt: Date? = null,

	val registerRecordCreateReason: String? = null,

	val registerRecordCreateAt: Date? = null,

	val isHeadOfficeTransfer: Boolean? = null,

	val headOfficeTransferAt: Date? = null,

	val headOfficeTransferRegisterAt: Date? = null,

	val isDisband: Boolean? = null,

	val disbandAt: Date? = null,

	val disbandRegisterAt: Date? = null,

	val disbandDeemedAt: Date? = null,

	val isLiquidation: Boolean? = null,

	val liquidationAt: Date? = null,

	val liquidationRegisterAt: Date? = null,

	val isRegisterRecordClosure: Boolean? = null,

	val registerRecordClosureAt: Date? = null,

	val settlementAt: Date? = null

)