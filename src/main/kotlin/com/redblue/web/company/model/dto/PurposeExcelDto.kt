package com.redblue.web.company.model.dto

import com.redblue.web.company.model.PurposeDetail

data class PurposeExcelDto(

	val companyName: String? = null,

	val detail: String? = null

) {
	companion object {

		fun toList(companyName: String?, purposeDetails: List<PurposeDetail>?): List<PurposeExcelDto> {
			val list = mutableListOf<PurposeExcelDto>()

			purposeDetails?.forEach { purposeDetail ->
				list.add(
					PurposeExcelDto(
						companyName = companyName,
						detail = purposeDetail.detail
					)
				)
			}
			return list
		}

	}
}