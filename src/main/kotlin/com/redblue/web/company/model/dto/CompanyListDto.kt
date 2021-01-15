package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Company
import org.springframework.data.domain.Page
import java.util.*

data class CompanyListDto(

	val id: String,

	val lawFirmId: String,

	val registerOffice: String? = null,

	val registerNumber: Int? = null,

	val companyName: String? = null,

	val companyAddress: String? = null,

	val expiredAt: Date? = null,

	val contactNumber: String? = "-"

) {

	companion object {

		fun to(companies: Page<Company>, startDate: Date?, endDate: Date?): List<CompanyListDto> {
			var list = mutableListOf<CompanyListDto>()
			for (company in companies.content) {

//				val minExpiredAt = company.executives.map { it.expiredAt }.first()
//
//				val contact = if(company.contacts!!.isEmpty()) {
//					null
//				} else {
//					company.contacts.first()
//				}
//
//				val number = if(contact == null) {
//					"-"
//				} else {
//					"(${contact.type}) ${contact.value}"
//				}
//
//				list.add(CompanyListDto(
//					id = company.id,
//					lawFirmId = company.lawFirmId,
//					registerOffice = company.registerOffice,
//					registerNumber = company.registerNumber,
//					companyName = company.companyName,
//					companyAddress = company.companyAddress,
//					expiredAt = minExpiredAt,
//					contactNumber = number
//				))
				list.add(CompanyListDto(
					id = company.id,
					lawFirmId = company.lawFirmId,
					registerOffice = company.registerOffice,
					registerNumber = company.registerNumber,
					companyName = company.companyName,
					companyAddress = company.companyAddress,
					expiredAt = company.expiredAt,
					contactNumber = "-"
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