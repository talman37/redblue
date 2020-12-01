package com.redblue.web.consult.repository

import com.redblue.web.consult.model.Consult
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface ConsultRepository: ConsultQueryDslRepository, JpaRepository<Consult, String> {

	fun findByCompanyId(companyId: String): List<Consult>

}

interface ConsultQueryDslRepository {

	fun findByLawFirmId(lawFirmId: String, pageable: Pageable): Page<Consult>

}