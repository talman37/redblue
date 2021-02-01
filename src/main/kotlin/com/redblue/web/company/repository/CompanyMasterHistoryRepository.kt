package com.redblue.web.company.repository

import com.redblue.web.company.model.CompanyMasterHistory
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyMasterHistoryRepository: JpaRepository<CompanyMasterHistory, String> {

	fun findByCompanyId(companyId: String): List<CompanyMasterHistory>
}