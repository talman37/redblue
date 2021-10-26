package com.redblue.web.company.service

import com.redblue.web.company.model.*
import com.redblue.web.company.model.dto.CompanyMasterUpdateDto
import com.redblue.web.company.model.dto.CompanyNoticeWayUpdateDto
import com.redblue.web.company.model.dto.CompanySubUpdateDto
import com.redblue.web.company.model.dto.SummaryResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface CompanyService {

	fun list(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, companyState: MutableList<String>, searchType: String?, positionTarget: String?, modifiedStartDate: Date?, modifiedEndDate: Date?, searchRange: String?): List<Company>

	fun listExcel(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, companyState: MutableList<String>, searchType: String?, positionTarget: String?, modifiedStartDate: Date?, modifiedEndDate: Date?, searchRange: String?): List<Company>

	fun listDm(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, companyState: MutableList<String>, searchType: String?, positionTarget: String?, modifiedStartDate: Date?, modifiedEndDate: Date?, searchRange: String?): List<Company>

	fun findExecutivesByCompanyId(companyId: String): List<Executive>

	fun totalCount(lawFirmId: String): Int

	fun manageCount(lawFirmId: String): Int

	fun findByName(lawFirmId: String, name: String): List<Company>

	fun getSummary(companyId: String): SummaryResponseDto

	fun detail(id: String): Company

	fun save(company: Company)

	fun updateCompanyMaster(id: String, dto: CompanyMasterUpdateDto): Date

	fun updateNoticeWay(id: String, dto: CompanyNoticeWayUpdateDto)

	fun updateCompanySub(id: String, dto: CompanySubUpdateDto): Date

	fun saveContact(contact: Contact)

	fun getHistories(id: String): CompanyHistory

	fun saveBranches(companyId: String, branches: List<CompanyBranch>)

	fun saveContacts(companyId: String, contacts: List<Contact>)

	fun saveExecutives(companyId: String, executives: List<Executive>): Date

	fun saveStockHolders(companyId: String, stockHolders: List<Stockholder>)

	fun savePurposeDetail(companyId: String, purposeDetails: List<PurposeDetail>)

	fun duplicateCheck(registerNumber: Int, registerOffice: String, companyDivision: String): Boolean

	fun delete(id: String)

	fun initExecutives(id: String)

}