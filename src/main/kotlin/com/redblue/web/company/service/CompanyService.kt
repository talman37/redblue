package com.redblue.web.company.service

import com.redblue.web.company.model.*
import com.redblue.web.company.model.dto.CompanyMasterUpdateDto
import com.redblue.web.company.model.dto.CompanySubUpdateDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface CompanyService {

	fun list(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, companyState: MutableList<String>): List<Company>

	fun findExecutivesByCompanyId(companyId: String): List<Executive>

	fun totalCount(lawFirmId: String): Int

	fun manageCount(lawFirmId: String): Int

	fun findByName(lawFirmId: String, name: String): List<Company>

	fun detail(id: String): Company

	fun save(company: Company)

	fun updateCompanyMaster(id: String, dto: CompanyMasterUpdateDto)

	fun updateCompanySub(id: String, dto: CompanySubUpdateDto)

	fun saveContact(contact: Contact)

	fun getHistories(id: String): CompanyHistory

	fun saveBranches(companyId: String, branches: List<CompanyBranch>)

	fun saveContacts(companyId: String, contacts: List<Contact>)

	fun saveExecutives(companyId: String, executives: List<Executive>)

	fun saveStockHolders(companyId: String, stockHolders: List<Stockholder>)

	fun savePurposeDetail(companyId: String, purposeDetails: List<PurposeDetail>)

}