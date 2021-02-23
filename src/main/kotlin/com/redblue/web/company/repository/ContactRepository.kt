package com.redblue.web.company.repository

import com.redblue.web.company.model.Contact
import org.springframework.data.jpa.repository.JpaRepository

interface ContactRepository: JpaRepository<Contact, String> {

	fun deleteByCompanyId(companyId: String)

}