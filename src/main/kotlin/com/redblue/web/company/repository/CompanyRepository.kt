package com.redblue.web.company.repository

import com.redblue.web.company.model.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.*

interface CompanyRepository : CompanyQueryDslRepository, JpaRepository<Company, String> {

	fun countBylawFirmId(lawFirmId: String): Int

	fun countBylawFirmIdAndCompanyState(lawFirmId: String, state: String): Int

	fun existsByRegisterNumberAndRegisterOfficeAndCompanyDivision(registerNumber: Int, registerOffice: String, companyDivision: String): Boolean

	@Modifying
	@Query("UPDATE Company c SET c.updatedAt = :updatedAt WHERE c.id = :id")
	fun saveUpdatedAt(id: String, updatedAt: Date)

}

interface CompanyQueryDslRepository {

	fun findByLawFirmId(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, companyState: MutableList<String>, searchType: String?, positionTarget: String?, modifiedStartDate: Date?, modifiedEndDate: Date?, searchRange: String?, registerOffice: String?): List<Company>

	fun findByLawFirmIdAndCompanyName(lawFirmId: String, companyName: String): List<Company>

	fun search(lawFirmId: String, q: String): Company

}