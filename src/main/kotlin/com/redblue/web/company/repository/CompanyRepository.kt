package com.redblue.web.company.repository

import com.redblue.web.company.model.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

interface CompanyRepository: CompanyQueryDslRepository, JpaRepository<Company, String> {

	fun countBylawFirmId(lawFirmId: String): Int

}

interface CompanyQueryDslRepository {

	fun findByLawFirmId(lawFirmId: String, q: String?): List<Company>

	fun search(lawFirmId: String, q: String): Company

}