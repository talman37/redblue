package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Contact
import com.redblue.web.company.model.Executive

data class SummaryResponseDto (

	var executives: List<Executive> = mutableListOf(),

	var companyMasterName: String? = null,

	val contacts: List<Contact>? = mutableListOf()

) {

	companion object {

		fun of(executives: List<Executive>, contacts: List<Contact>): SummaryResponseDto {
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

			return SummaryResponseDto(
				executives = executives,
				companyMasterName = master,
				contacts = contacts
			)

		}

	}

}