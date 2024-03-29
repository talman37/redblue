package com.redblue.web.company.model.dto

import com.redblue.web.company.model.CompanyBranch

data class CompanyBranchUpdateDto(

	val branches: List<CompanyBranch> = emptyList()

) {

	fun to(companyId: String): List<CompanyBranch> {
		val savedList = mutableListOf<CompanyBranch>()
		this.branches.forEach {
			if(it != null) {
				it.companyId = companyId
				savedList.add(it)
			}
		}
		return savedList
	}

}