package com.redblue.web.company.model.dto

import com.redblue.web.company.model.*
import net.bytebuddy.utility.RandomString
import java.util.*
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Temporal
import javax.persistence.TemporalType

data class CompanyCreateDto(

	val id: String? = "CM" + RandomString.make(30),

	val baseCompanyId: String? = null,

	val registerOffice: String? = null,

	val registerNumber: Int? = null,

	val companyNumber1: Int? = null,

	val companyNumber2: Int? = null,

	val companyName: String? = null,

	val companyDivision: String?,

	val companyManageNumber: String? = null,

	val companyManageState: String? = null,

	val companyState: String? = null,

	@Enumerated(EnumType.STRING)
	val displayCompanyType: Company.DisplayCompanyType? = Company.DisplayCompanyType.FRONT,

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

	val noticeWayUpdatedAt: Date? = null,

	val etc: String? = null,

	val convertibleBond: String? = null,

	val stockPurchaseOption: String? = null,

	val companyFormationAt: Date? = null,

	val registerRecordCreateReason: String? = null,

	val registerRecordCreateAt: Date? = null,

	val isHeadOfficeTransfer: Boolean? = null,

	val headOfficeTransferAt: Date? = null,

	val isDisband: Boolean? = null,

	val disbandAt: Date? = null,

	val disbandDeemedAt: Date? = null,

	val isLiquidation: Boolean? = null,

	val liquidationAt: Date? = null,

	val isRegisterRecordClosure: Boolean? = null,

	val registerRecordClosureAt: Date? = null,

	val settlementMonth: Int? = null,

	val recommender: String? = null,

	var stock: Stock? = null,

	var executives: List<Executive> = emptyList(),

	var stockholders: List<Stockholder> = emptyList(),

	var contacts: List<Contact> = emptyList(),

	var purposeDetail: PurposeDetail? = null

) {

	fun of(lawFirmId: String): Company {
		val stock = this.stock?.copy(
			companyId = this.id
		)

		val contacts = mutableListOf<Contact>()
		this.contacts.forEach {
			contacts.add(
				Contact(
					id = it.id,
					companyId = this.id,
					type = it.type,
					value = it.value,
					memo = it.memo
				)
			)
		}

		val executives = mutableListOf<Executive>()
		this.executives.forEach {
			executives.add(
				Executive(
					id = it.id,
					companyId = this.id,
					detail = it.detail,
					type = it.type,
					name = it.name,
					registrationNumber1 = it.registrationNumber1,
					registrationNumber2 = it.registrationNumber2,
					address = it.address,
					position = it.position,
					inauguratedAt = it.inauguratedAt,
					term = it.term,
					updatedReason = it.updatedReason,
					expiredAt = it.expiredAt,
					stockCount = it.stockCount
				)
			)
		}

		val purposeDetail = this.purposeDetail?.copy(
			companyId = this.id!!
		)

		return Company(
			id = this.id!!,
			lawFirmId = lawFirmId,
			registerOffice = this.registerOffice,
			registerNumber = this.registerNumber,
			companyNumber1 = this.companyNumber1,
			companyNumber2 = this.companyNumber2,
			companyName = this.companyName,
			companyDivision = this.companyDivision,
			companyManageNumber = this.companyManageNumber,
			companyManageState = this.companyManageState,
			companyState = this.companyState,
			displayCompanyType = this.displayCompanyType,
			companySubName = this.companySubName,
			companyAddress = this.companyAddress,
			companyPostalCode = this.companyPostalCode,
			businessNumber = this.businessNumber,
			businessType = this.businessType,
			businessCondition = this.businessCondition,
			deliveryPlace = this.deliveryPlace,
			deliveryPlacePostalCode = this.deliveryPlacePostalCode,
			noticeWay = this.noticeWay,
			etc = this.etc,
			convertibleBond = this.convertibleBond,
			stockPurchaseOption = this.stockPurchaseOption,
			companyFormationAt = this.companyFormationAt,
			registerRecordCreateReason = this.registerRecordCreateReason,
			registerRecordCreateAt = this.registerRecordCreateAt,
			isHeadOfficeTransfer = this.isHeadOfficeTransfer,
			headOfficeTransferAt = this.headOfficeTransferAt,
			isDisband = this.isDisband,
			disbandDeemedAt = this.disbandDeemedAt,
			isLiquidation = this.isLiquidation,
			liquidationAt = this.liquidationAt,
			isRegisterRecordClosure = this.isRegisterRecordClosure,
			settlementMonth = this.settlementMonth,
			recommender = this.recommender,
			executives = executives,
			stock = stock,
			contacts = contacts,
			purposeDetail = purposeDetail
		)



	}


}