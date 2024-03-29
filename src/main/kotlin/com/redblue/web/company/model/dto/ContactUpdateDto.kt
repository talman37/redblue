package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Contact

data class ContactUpdateDto(

	val contacts: List<Contact> = emptyList()

) {

	fun to(companyId: String): List<Contact> {
		val savedList = mutableListOf<Contact>()
		this.contacts.forEach {
			if(it != null) {
				it.companyId = companyId
				savedList.add(it)
			}
		}
		return savedList
	}

}