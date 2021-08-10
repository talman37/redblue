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

	val companyNumber1: String? = null,

	val companyNumber2: String? = null,

	val companyName: String? = null,

	val companyDivision: String?,

	val companyManageNumber: String? = null,

	val companyManageState: String? = null,

	val companyState: String? = null,

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

	val noticeWay: String? = null,

	@Temporal(TemporalType.DATE)
	val noticeWayUpdatedAt: Date? = null,

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

	val recommender: String? = null,

	val precautions: String? = null,

	@Temporal(TemporalType.DATE)
	val createdAt: Date? = Date(),

	@Temporal(TemporalType.DATE)
	val updatedAt: Date? = Date(),

	@Transient
	var stock: Stock? = null,

	@Transient
	var executives: MutableList<Executive>? = mutableListOf(),

	@Transient
	var expiredAt: Date? = null,

	@Transient
	var companyMasterName: String? = null,

	@Transient
	var stockholders: List<Stockholder>? = emptyList(),

	@Transient
	var contacts: MutableList<Contact>? = mutableListOf(),

	@Transient
	var purposeDetail: List<PurposeDetail>? = emptyList(),

	@Transient
	var branches: List<CompanyBranch>? = emptyList()

) {
	enum class DisplayCompanyType {
		FRONT,
		BACK
	}
}