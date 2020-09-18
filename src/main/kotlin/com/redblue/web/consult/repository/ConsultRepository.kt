package com.redblue.web.consult.repository

import com.redblue.web.consult.model.Consult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface ConsultRepository: ConsultQueryDslRepository, JpaRepository<Consult, String> {

}

interface ConsultQueryDslRepository {

	fun findByLawFirmId(lawFirmId: String): List<Consult>

}