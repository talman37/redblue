package com.redblue.web.company.repository

import com.redblue.web.company.model.StockHistory
import org.springframework.data.jpa.repository.JpaRepository

interface StockHistoryRepository: JpaRepository<StockHistory, String> {

	fun findByCompanyId(companyId: String): List<StockHistory>

	fun findByCompanyIdOrderByIssuedAtDesc(companyId: String): List<StockHistory>

	fun deleteByCompanyId(companyId: String)

}
