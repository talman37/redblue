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

	val registerOffice: String? = null,

	val registerNumber: Int? = null,

	val companyNumber1: Int? = null,

	val companyNumber2: Int? = null,

	val companyName: String? = null,

	val companyDivision: String?,

	@Enumerated(EnumType.STRING)
	val displayCompanyType: DisplayCompanyType? = DisplayCompanyType.FRONT,

	val companySubName: String? = null,

	@Temporal(TemporalType.DATE)
	val companyUpdatedAt: Date? = null,

	val companyAddress: String? = null,

	val companyPostalCode: String? = null,

	@Temporal(TemporalType.DATE)
	val companyAddressUpdatedAt: Date? = null,

	val businessNumber: String? = null,

	val businessType: String? = null,

	val businessCondition: String? = null,

	val deliveryPlace: String? = null,

	val deliveryPlacePostalCode: String? = null,

	val etc: String? = null,

	val convertibleBond: String? = null,

	val stockPurchaseOption: String? = null,

	@Temporal(TemporalType.DATE)
	val companyFormationAt: Date? = null,

	val registerRecordCreateReason: String? = null,

	@Temporal(TemporalType.DATE)
	val registerRecordCreateAt: Date? = null,

	val isHeadOfficeTransfer: Boolean? = null,

	@Temporal(TemporalType.DATE)
	val headOfficeTransferAt: Date? = null,

	val isDisband: Boolean? = null,

	@Temporal(TemporalType.DATE)
	val disbandAt: Date? = null,

	@Temporal(TemporalType.DATE)
	val disbandDeemedAt: Date? = null,

	val isLiquidation: Boolean? = null,

	@Temporal(TemporalType.DATE)
	val liquidationAt: Date? = null,

	val isRegisterRecordClosure: Boolean? = null,

	@Temporal(TemporalType.DATE)
	val registerRecordClosureAt: Date? = null,

	val settlementMonth: Int? = null,

	@Transient
	var stock: Stock? = null,

	@OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "companyId")
	var executives: List<Executive> = emptyList(),

	@Transient
	var stockholders: List<Stockholder> = emptyList()

) {
	enum class DisplayCompanyType{
		FRONT,
		BACK
	}
}