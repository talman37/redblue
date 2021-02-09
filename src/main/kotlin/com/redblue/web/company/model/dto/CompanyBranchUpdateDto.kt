package com.redblue.web.company.model.dto

import com.redblue.web.company.model.CompanyBranch

data class CompanyBranchUpdateDto(

	val branches: List<CompanyBranch> = emptyList()

) {

	fun to(companyId: String): List<CompanyBranch> {
		this.branches.forEach {
			it.companyId = companyId
		}
		return this.branches
	}

}