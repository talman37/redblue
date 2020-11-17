package com.redblue.web.company.service

import com.redblue.web.company.model.Company
import com.redblue.web.company.model.Contact
import com.redblue.web.company.model.dto.CompanyMasterUpdateDto
import com.redblue.web.company.model.dto.CompanySubUpdateDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface CompanyService {

	fun list(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, pageable: Pageable): Page<Company>

	fun count(lawFirmId: String): Int

	fun findByName(lawFirmId: String, name: String): List<Company>

	fun detail(id: String): Company

	fun save(company: Company)

	fun updateCompanyMaster(id: String, dto: CompanyMasterUpdateDto)

	fun updateCompanySub(id: String, dto: CompanySubUpdateDto)

	fun saveContact(contact: Contact)

}