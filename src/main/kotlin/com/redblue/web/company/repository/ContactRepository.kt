package com.redblue.web.company.repository

import com.redblue.web.company.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository: JpaRepository<Contact, String> {

	fun findByCompanyId(companyId: String): MutableList<Contact>

	fun findByCompanyIdIn(companyIds: List<String>): MutableList<Contact>

	fun deleteByCompanyId(companyId: String)

}