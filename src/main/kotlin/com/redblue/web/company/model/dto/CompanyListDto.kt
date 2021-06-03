package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Company
import com.redblue.web.company.model.Contact
import org.springframework.data.domain.Page
import java.util.*

data class CompanyListDto(

	val id: String,

	val lawFirmId: String,

	val registerOffice: String? = null,

	val registerNumber: Int? = null,

	val companyName: String? = null,

	val companyDivision: String? = null,

	val displayCompanyType: String? = null,

	val companyAddress: String? = null,

	val expiredAt: Date? = null,

	val contactNumber: String? = "-",

	val companyMasterName: String? = null,

	val contacts: MutableList<Contact>? = mutableListOf()

) {

	companion object {

		fun of(companies: Page<Company>, startDate: Date?, endDate: Date?): List<CompanyListDto> {
			var list = mutableListOf<CompanyListDto>()
			for (company in companies.content) {
				var dto = CompanyListDto(
					id = company.id,
					lawFirmId = company.lawFirmId,
					registerOffice = company.registerOffice,
					registerNumber = company.registerNumber,
					companyName = company.companyName,
					companyDivision = company.companyDivision,
					displayCompanyType = company.displayCompanyType?.name,
					companyAddress = company.companyAddress,
					expiredAt = company.executives.first().expiredAt,
					contacts = company.contacts,
					companyMasterName = company.companyMasterName
				)
				list.add(dto)
			}
			return list
		}

	}

}