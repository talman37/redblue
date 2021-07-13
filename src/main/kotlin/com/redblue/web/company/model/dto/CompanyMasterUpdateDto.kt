package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Company
import java.util.*

data class CompanyMasterUpdateDto(

	val registerNumber: Int? = null,

	val registerOffice: String? = null,

	val companyNumber1: String? = null,

	val companyNumber2: String? = null,

	val companyName: String? = null,

	val companyDivision: String?,

	val companyManageNumber: String? = null,

	val companyManageState: String? = null,

	val companyState: String? = null,

	val displayCompanyType: Company.DisplayCompanyType? = Company.DisplayCompanyType.FRONT,

	val companySubName: String? = null,

	val recommender: String? = null,

	val precautions: String? = null,

	val companyFormationAt: Date? = null

)