package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Contact

data class ContactExcelDto(

	val companyName: String? = null,

	val type: String? = null,

	val value: String? = null,

	val memo: String? = null

) {
	companion object {

		fun toList(companyName: String?, contacts: List<Contact>?): List<ContactExcelDto> {
			val list = mutableListOf<ContactExcelDto>()
			contacts?.forEach { contact ->
				list.add(
					ContactExcelDto(
						companyName = companyName,
						type = contact.type?.text,
						value= contact.value,
						memo =  contact.memo
					)
				)
			}
			return list
		}

	}
}