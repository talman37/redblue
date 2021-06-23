package com.redblue.web.company.repository

import com.redblue.web.company.model.Contact
import org.springframework.data.jpa.repository.JpaRepository

interface ContactRepository: JpaRepository<Contact, String> {

	fun findByCompanyId(companyId: String): List<Contact>

	fun deleteByCompanyId(companyId: String)

}