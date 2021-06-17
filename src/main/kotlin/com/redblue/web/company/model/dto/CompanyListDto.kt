package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Company
import com.redblue.web.company.model.Contact
import com.redblue.web.company.model.Executive
import org.springframework.data.domain.Page
import java.util.*

data class CompanyListDto(

	val id: String,

	val lawFirmId: String,

	val registerOffice: String? = null,

	val registerNumber: Int? = null,

	val companyName: String? = null,

	val companyState: String? = null,

	val companyDivision: String? = null,

	val displayCompanyType: String? = null,

	val companyAddress: String? = null,

	var companyMasterName: String? = null,

	var executives: MutableList<Executive> = mutableListOf(),

	val contacts: MutableList<Contact>? = mutableListOf()

) {

	companion object {

		fun of(companies: List<Company>): List<CompanyListDto> {
			val list = mutableListOf<CompanyListDto>()
			for (company in companies) {
				var name = company.companyName
				if(company.displayCompanyType == Company.DisplayCompanyType.FRONT) {
					name = "(" + company.companyDivision?.first() + ")" + company.companyName
				} else if(company.displayCompanyType == Company.DisplayCompanyType.BACK){
					name = company.companyName + "(" + company.companyDivision?.first() + ")"
				}

				val dto = CompanyListDto(
					id = company.id,
					lawFirmId = company.lawFirmId,
					registerOffice = company.registerOffice,
					registerNumber = company.registerNumber,
					companyName = name,
					companyState = company.companyState,
					companyDivision = company.companyDivision,
					displayCompanyType = company.displayCompanyType?.name,
					companyAddress = company.companyAddress,
					executives = company.executives,
					contacts = company.contacts
				)
				var master: String? = ""

				for (executive in company.executives) {
					if(executive.position == "대표이사") {
						master = executive.name
						break
					} else if(executive.position == "사내이사") {
						master = executive.name
					} else if(executive.position == "공동대표이사") {
						master = executive.name
					}
				}
				dto.companyMasterName = master

				val savedContactType = company.contacts?.map { c -> c.type }.orEmpty()
				for (value in Contact.Type.values()) {
					if(!savedContactType.contains(value)) {
						dto.contacts?.add(Contact(type = value, value = ""))
					}
				}
				list.add(dto)
			}
			return list
		}

	}

}