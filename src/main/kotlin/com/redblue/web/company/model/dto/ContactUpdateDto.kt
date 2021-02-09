package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Contact

data class ContactUpdateDto(

	val contacts: List<Contact> = emptyList()

) {

	fun to(companyId: String): List<Contact> {
		this.contacts.forEach {
			it.companyId = companyId
		}
		return this.contacts
	}

}