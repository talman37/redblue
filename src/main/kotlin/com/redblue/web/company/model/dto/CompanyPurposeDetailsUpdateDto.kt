package com.redblue.web.company.model.dto

import com.redblue.web.company.model.PurposeDetail
import java.util.*

data class CompanyPurposeDetailsUpdateDto(

	val purposeDetail: List<PurposeDetail> = emptyList()

) {

	fun to(companyId: String): List<PurposeDetail> {
		this.purposeDetail.forEachIndexed { index, purposeDetail ->
			purposeDetail.companyId = companyId
			purposeDetail.ordinal = index
			purposeDetail.detailUpdatedAt = Date()
		}
		return this.purposeDetail
	}

}