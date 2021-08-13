package com.redblue.web.company.repository

import com.redblue.web.company.model.ExecutiveHistory
import org.springframework.data.jpa.repository.JpaRepository

interface ExecutiveHistoryRepository: JpaRepository<ExecutiveHistory, String>{

	fun findByCompanyId(companyId: String): List<ExecutiveHistory>

	fun findByCompanyIdOrderByIssuedAtDesc(companyId: String): List<ExecutiveHistory>

	fun deleteByCompanyId(companyId: String)

}