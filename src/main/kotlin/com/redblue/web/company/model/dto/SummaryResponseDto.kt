package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Contact
import com.redblue.web.company.model.Executive

data class SummaryResponseDto (

	var executives: List<Executive> = mutableListOf(),

	var companyMasterName: String? = null,

	var companyManageNumber: String? = null,

	val contacts: MutableList<Contact>? = mutableListOf(),

	val precautions: String? = null

) {

	companion object {

		fun of(executives: List<Executive>, contacts: MutableList<Contact>, precautions: String?, companyManageNumber: String?): SummaryResponseDto {
			var master: String? = ""

			for (executive in executives) {
				if(executive.position == "대표이사") {
					master = executive.name
					break
				} else if(executive.position == "사내이사") {
					master = executive.name
				} else if(executive.position == "공동대표이사") {
					master = executive.name
				}
			}

			val savedContactType = contacts.map { c -> c.type }
			for (value in Contact.Type.values()) {
				if(!savedContactType.contains(value)) {
					contacts.add(Contact(type = value, value = ""))
				}
			}

			return SummaryResponseDto(
				executives = executives,
				companyMasterName = master,
				companyManageNumber = companyManageNumber,
				contacts = contacts,
				precautions = precautions
			)

		}

	}

}
