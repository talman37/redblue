package com.redblue.web.company.model

import net.bytebuddy.utility.RandomString
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "COMPANIES")
data class Company(

	@Id
	val id: String = "CM" + RandomString.make(30),

	val lawFirmId: String,

	val baseCompanyId: String? = null,

	val registerOffice: String,

	val registerNumber: Int,

	val companyNumber1: Int,

	val companyNumber2: Int,

	val companyName: String,

	@Enumerated(EnumType.STRING)
	val displayCompanyType: DisplayCompanyType = DisplayCompanyType.FRONT,

	val companySubName: String,

	@Temporal(TemporalType.DATE)
	val companyUpdatedAt: Date,

	val companyAddress: String,

	val companyPostalCode: String,

	@Temporal(TemporalType.DATE)
	val companyAddressUpdatedAt: Date,

	@Temporal(TemporalType.DATE)
	val companyAddressRegisterUpdatedAt: Date,

	val businessNumber: String,

	val businessType: String,

	val businessCondition: String,

	val deliveryPlace: String,

	val deliveryPlacePostalCode: String,

	val etc: String,

	val convertibleBond: String,

	val stockPurchaseOption: String,

	@Temporal(TemporalType.DATE)
	val companyFormationAt: Date? = null,

	val registerRecordCreateReason: String? = null,

	@Temporal(TemporalType.DATE)
	val registerRecordCreateAt: Date? = null,

	val isHeadOfficeTransfer: Boolean? = null,

	@Temporal(TemporalType.DATE)
	val headOfficeTransferAt: Date? = null,

	@Temporal(TemporalType.DATE)
	val headOfficeTransferRegisterAt: Date? = null,

	val isDisband: Boolean? = null,

	@Temporal(TemporalType.DATE)
	val disbandAt: Date? = null,

	@Temporal(TemporalType.DATE)
	val disbandRegisterAt: Date? = null,

	@Temporal(TemporalType.DATE)
	val disbandDeemedAt: Date? = null,

	val isLiquidation: Boolean? = null,

	@Temporal(TemporalType.DATE)
	val liquidationAt: Date? = null,

	@Temporal(TemporalType.DATE)
	val liquidationRegisterAt: Date? = null,

	val isRegisterRecordClosure: Boolean? = null,

	@Temporal(TemporalType.DATE)
	val registerRecordClosureAt: Date? = null,

	val settlementMonth: Int? = null,

	@Transient
	var stock: Stock? = null,

	@Transient
	var executives: List<Executive> = emptyList()

) {
	enum class DisplayCompanyType{
		FRONT,
		BACK
	}
}