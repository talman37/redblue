package com.redblue.web.company.repository

import com.redblue.web.company.model.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRepository: JpaRepository<Stock, String> {

	fun findByCompanyId(companyId: String): Stock?

}