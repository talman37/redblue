package com.redblue.web.consult.repository

import com.redblue.web.consult.model.Consult
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

interface ConsultRepository: ConsultQueryDslRepository, JpaRepository<Consult, String> {

	fun findByCompanyId(companyId: String): List<Consult>

	fun findByCompanyIdOrderByCreatedAtDesc(companyId: String): List<Consult>

	fun deleteByCompanyId(companyId: String)

}

interface ConsultQueryDslRepository {

	fun findByLawFirmId(lawFirmId: String, searchValue:String?, startDate: Date?, endDate: Date?, progress: List<Consult.Progress>?, pageable: Pageable): Page<Consult>

	fun details(id: String): Consult

}