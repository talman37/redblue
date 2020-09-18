package com.redblue.web.company.repository

import com.redblue.web.company.model.Stockholder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StockholderRepository: JpaRepository<Stockholder, String> {

	fun findByCompanyId(companyId: String): List<Stockholder>

}