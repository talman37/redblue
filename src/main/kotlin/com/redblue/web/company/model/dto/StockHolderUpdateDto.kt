package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Stockholder

data class StockHolderUpdateDto(

	val stockholders: List<Stockholder> = emptyList()
) {

	fun to(companyId: String): List<Stockholder> {
		this.stockholders.forEach {
			it.companyId = companyId
		}
		return this.stockholders
	}

}