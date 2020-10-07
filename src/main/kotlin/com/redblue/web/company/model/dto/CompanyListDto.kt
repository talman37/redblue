package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Company
import java.util.*

data class CompanyListDto(

	val id: String,

	val lawFirmId: String,

	val registerOffice: String? = null,

	val registerNumber: Int? = null,

	val companyName: String? = null,

	val companyAddress: String? = null,

	val expiredAt: Date? = null

) {

	companion object {

		fun to(companies: List<Company>, startDate: Date?, endDate: Date?): List<CompanyListDto> {
			var list = mutableListOf<CompanyListDto>()
			for (company in companies) {

				val minExpiredAt = company.executives.map { it.expiredAt }.first()

				list.add(CompanyListDto(
					id = company.id,
					lawFirmId = company.lawFirmId,
					registerOffice = company.registerOffice,
					registerNumber = company.registerNumber,
					companyName = company.companyName,
					companyAddress = company.companyAddress,
					expiredAt = minExpiredAt
				))
			}

			startDate?.let{
				list = list.filter{
					it.expiredAt?.let {date ->
						date >= startDate
					} ?: false
				}.toMutableList()
			}

			endDate?.let{
				list = list.filter{
					it.expiredAt?.let {date ->
						date <= endDate
					} ?: false
				}.toMutableList()
			}

			return list
		}

	}

}