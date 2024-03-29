package com.redblue.web.company.repository

import com.redblue.web.company.model.CompanySubHistory
import org.springframework.data.jpa.repository.JpaRepository

interface CompanySubHistoryRepository: JpaRepository<CompanySubHistory, String>{

	fun findByCompanyId(companyId: String): List<CompanySubHistory>

	fun findByCompanyIdOrderByIssuedAtDesc(companyId: String): List<CompanySubHistory>

	fun deleteByCompanyId(companyId: String)

}