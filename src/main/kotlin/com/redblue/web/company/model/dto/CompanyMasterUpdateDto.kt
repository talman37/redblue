package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Company

data class CompanyMasterUpdateDto(

	val registerNumber: Int? = null,

	val registerOffice: String? = null,

	val companyNumber1: Int? = null,

	val companyNumber2: Int? = null,

	val companyName: String? = null,

	val companyDivision: String?,

	val companyManageNumber: String? = null,

	val companyManageState: String? = null,

	val companyState: String? = null,

	val displayCompanyType: Company.DisplayCompanyType? = Company.DisplayCompanyType.FRONT,

	val companySubName: String? = null,

	val recommender: String? = null,

	val precautions: String? = null

)