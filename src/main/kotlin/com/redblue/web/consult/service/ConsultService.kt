package com.redblue.web.consult.service

import com.redblue.web.consult.model.Consult
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ConsultService {

	fun list(lawFirmId: String, pageable: Pageable): Page<Consult>

	fun findByCompanyId(companyId: String): List<Consult>

}