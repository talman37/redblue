package com.redblue.web.company.model

import net.bytebuddy.utility.RandomString
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "COMPANY_BRANCHES")
data class CompanyBranch(

	@Id
	val id: String = "CB" + RandomString.make(30),

	var companyId: String? = null,

	val name: String? = null,

	val address: String? = null,

	val postalCode: String? = null

)