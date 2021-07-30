package com.redblue.web.company.repository

import com.redblue.web.company.model.Executive
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExecutiveRepository: JpaRepository<Executive, String> {

	fun findByCompanyIdOrderByExpiredAt(companyId: String): List<Executive>

	fun findByCompanyIdIn(companyIds: List<String>): List<Executive>

	fun deleteByCompanyId(companyId: String)

}