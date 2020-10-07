package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Executive
import java.util.*

data class ExecutiveExcelDto(

	val companyName: String? = null,

	val detail: String? = null,

	val type: String? = null,

	val name: String? = null,

	val position: String? = null,

	val registrationNumber1: String? = null,

	val registrationNumber2: String? = null,

	val address: String? = null,

	val inauguratedAt: Date? = null,

	val updatedReason: String? = null,

	val updatedAt: Date? = null,

	val expiredAt: Date? = null,

	val stockCount: Int = 0

) {
	companion object {

		fun toList(companyName: String?, executives: List<Executive>?): List<ExecutiveExcelDto> {
			val list = mutableListOf<ExecutiveExcelDto>()

			executives?.forEach { executive ->
				list.add(
					ExecutiveExcelDto(
						companyName = companyName,
						detail = executive.detail,
						type = executive.type,
						name = executive.name,
						position = executive.position,
						registrationNumber1 = executive.registrationNumber1,
						registrationNumber2 = executive.registrationNumber2,
						address = executive.address,
						inauguratedAt = executive.inauguratedAt,
						updatedReason = executive.updatedReason,
						updatedAt = executive.updatedAt,
						expiredAt = executive.expiredAt,
						stockCount = executive.stockCount ?: 0
					)
				)
			}
			return list
		}

	}
}