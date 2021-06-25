package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Company
import com.redblue.web.company.model.Executive
import java.util.*

data class CompanyExcelDto(

	val registerOffice: String? = null,

	val registerNumber: Int? = null,

	val companyNumber1: String? = null,

	val companyNumber2: String? = null,

	val companyDivision: String?,

	val companyManageNumber: String? = null,

	val companyManageState: String? = null,

	val companyState: String? = null,

	val companyName: String? = null,

	val companyUpdatedAt: Date? = null,

	val businessNumber: String? = null,

	val businessType: String? = null,

	val businessCondition: String? = null,

	val deliveryPlace: String? = null,

	val deliveryPlacePostalCode: String? = null,

	val companySubName: String? = null,

	val companyAddress: String? = null,

	val companyPostalCode: String? = null,

	val companyAddressUpdatedAt: Date? = null,

	val noticeWay: String? = null,

	val noticeWayUpdatedAt: Date? = null,

	val etc: String? = null,

	val convertibleBond: String? = null,

	val stockPurchaseOption: String? = null,

	val companyFormationAt: Date? = null,

	val registerRecordCreateReason: String? = null,

	val registerRecordCreateAt: Date? = null,

	val isHeadOfficeTransfer: Boolean? = false,

	val headOfficeTransferAt: Date? = null,

	val isDisband: Boolean? = false,

	val disbandAt: Date? = null,

	val disbandDeemedAt: Date? = null,

	val isLiquidation: Boolean? = false,

	val liquidationAt: Date? = null,

	val isRegisterRecordClosure: Boolean? = false,

	val registerRecordClosureAt: Date? = null,

	val settlementMonth: Int? = null,

	val recommender: String? = null,

	val executives: List<Executive>? = emptyList(),

	val id: String? = null

) {
	companion object {

		fun toList(companies: List<Company>): List<CompanyExcelDto> {
			val list = mutableListOf<CompanyExcelDto>()
			for (company in companies) {
				list.add(
					CompanyExcelDto(
						registerOffice = company.registerOffice,
						registerNumber = company.registerNumber,
						companyNumber1 = company.companyNumber1,
						companyNumber2 = company.companyNumber2,
						companyDivision = company.companyDivision,
						companyManageNumber = company.companyManageNumber,
						companyManageState = company.companyManageState,
						companyState = company.companyState,
						companyName = company.companyName,
						companyUpdatedAt = company.companyUpdatedAt,
						businessNumber = company.businessNumber,
						businessType = company.businessType,
						businessCondition = company.businessCondition,
						deliveryPlace = company.deliveryPlace,
						deliveryPlacePostalCode = company.deliveryPlacePostalCode,
						companySubName = company.companySubName,
						companyAddress = company.companyAddress,
						companyPostalCode = company.companyPostalCode,
						companyAddressUpdatedAt = company.companyAddressUpdatedAt,
						noticeWay = company.noticeWay,
						noticeWayUpdatedAt = company.noticeWayUpdatedAt,
						etc = company.etc,
						convertibleBond = company.convertibleBond,
						stockPurchaseOption = company.stockPurchaseOption,
						companyFormationAt = company.companyFormationAt,
						registerRecordCreateReason = company.registerRecordCreateReason,
						registerRecordCreateAt = company.registerRecordCreateAt,
						isHeadOfficeTransfer = company.isHeadOfficeTransfer,
						headOfficeTransferAt = company.headOfficeTransferAt,
						isDisband = company.isDisband,
						disbandAt = company.disbandAt,
						disbandDeemedAt = company.disbandDeemedAt,
						isLiquidation = company.isLiquidation,
						liquidationAt = company.liquidationAt,
						isRegisterRecordClosure = company.isRegisterRecordClosure,
						registerRecordClosureAt = company.registerRecordClosureAt,
						settlementMonth = company.settlementMonth,
						recommender = company.recommender,
						executives = company.executives,
						id = company.id
					)
				)
			}
			return list
		}

	}
}