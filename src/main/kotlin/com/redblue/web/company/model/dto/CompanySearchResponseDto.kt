package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Company
import java.util.*

data class CompanySearchResponseDto (

	val companyName: String?,

	val expiredAt: Date?

) {
	companion object {

		fun of(companies: List<Company>): List<CompanySearchResponseDto> {
			var list = mutableListOf<CompanySearchResponseDto>()
			for (company in companies) {

				list.add(CompanySearchResponseDto(
					companyName = company.companyName,
					expiredAt = null
				))
			}
			return list
		}

	}
}