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

	var contacts: List<Contact> = emptyList()

) {

	fun of(lawFirmId: String, dto: CompanyCreateDto): Company {
		val stock = dto.stock?.copy(
			companyId = dto.id
		)

		val contacts = mutableListOf<Contact>()
		dto.contacts.forEach {
			contacts.add(
				Contact(
					id = it.id,
					companyId = dto.id,
					type = it.type,
					value = it.value,
					memo = it.memo
				)
			)
		}

		val executives = mutableListOf<Executive>()
		dto.executives.forEach {
			executives.add(
				Executive(
					id = it.id,
					companyId = dto.id,
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
		return Company(
			id = dto.id!!,
			lawFirmId = lawFirmId,
			registerOffice = dto.registerOffice,
			registerNumber = dto.registerNumber,
			companyNumber1 = dto.companyNumber1,
			companyNumber2 = dto.companyNumber2,
			companyName = dto.companyName,
			companyDivision = dto.companyDivision,
			companyManageNumber = dto.companyManageNumber,
			companyManageState = dto.companyManageState,
			companyState = dto.companyState,
			displayCompanyType = dto.displayCompanyType,
			companySubName = dto.companySubName,
			companyAddress = dto.companyAddress,
			companyPostalCode = dto.companyPostalCode,
			businessNumber = dto.businessNumber,
			businessType = dto.businessType,
			businessCondition = dto.businessCondition,
			deliveryPlace = dto.deliveryPlace,
			deliveryPlacePostalCode = dto.deliveryPlacePostalCode,
			noticeWay = dto.noticeWay,
			etc = dto.etc,
			convertibleBond = dto.convertibleBond,
			stockPurchaseOption = dto.stockPurchaseOption,
			companyFormationAt = dto.companyFormationAt,
			registerRecordCreateReason = dto.registerRecordCreateReason,
			registerRecordCreateAt = dto.registerRecordCreateAt,
			isHeadOfficeTransfer = dto.isHeadOfficeTransfer,
			headOfficeTransferAt = dto.headOfficeTransferAt,
			isDisband = dto.isDisband,
			disbandDeemedAt = dto.disbandDeemedAt,
			isLiquidation = dto.isLiquidation,
			liquidationAt = dto.liquidationAt,
			isRegisterRecordClosure = dto.isRegisterRecordClosure,
			settlementMonth = dto.settlementMonth,
			recommender = dto.recommender,
			executives = executives,
			stock = stock,
			contacts = contacts
		)



	}


}