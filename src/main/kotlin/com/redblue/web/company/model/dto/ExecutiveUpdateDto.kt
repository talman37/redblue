package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Executive

data class ExecutiveUpdateDto(

	val executives: List<Executive> = emptyList()

) {

	fun to(companyId: String): List<Executive> {
		this.executives.forEach {
			it.companyId = companyId
		}
		return this.executives
	}

}