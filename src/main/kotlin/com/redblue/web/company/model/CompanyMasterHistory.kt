package com.redblue.web.company.model

import net.bytebuddy.utility.RandomString
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "COMPANY_MASTER_HISTORY")
data class CompanyMasterHistory (

	@Id
	val id: String = "CMMH" + RandomString.make(28),

	@Enumerated(EnumType.STRING)
	val type: IssuedType? = null,

	val companyId: String? = null,

	val registerNumber: Int? = null,

	val registerOffice: String? = null,

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

	val recommender: String? = null,

	@Temporal(TemporalType.DATE)
	val issuedAt: Date? = null

)