package com.redblue.web.company.repository

import com.redblue.web.company.model.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface CompanyRepository: CompanyQueryDslRepository, JpaRepository<Company, String> {


	fun findByLawFirmId(lawFirmId: String): List<Company>

	fun countBylawFirmId(lawFirmId: String): Int

}

interface CompanyQueryDslRepository {

	fun search(lawFirmId: String, q: String): Company

}