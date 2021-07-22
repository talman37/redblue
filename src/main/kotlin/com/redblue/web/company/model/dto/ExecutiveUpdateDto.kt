package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Executive

data class ExecutiveUpdateDto(

	val executives: List<Executive> = emptyList()

) {

	fun to(companyId: String): List<Executive> {
		val savedList = mutableListOf<Executive>()
		this.executives.forEach {
			if(it != null) {
				it.companyId = companyId
				savedList.add(it)
			}
		}
		return savedList
	}

}