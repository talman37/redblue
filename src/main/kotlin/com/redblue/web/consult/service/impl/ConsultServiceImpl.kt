package com.redblue.web.consult.service.impl

import com.redblue.web.consult.model.Consult
import com.redblue.web.consult.repository.ConsultRepository
import com.redblue.web.consult.service.ConsultService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ConsultServiceImpl(
	private val consultRepository: ConsultRepository
) : ConsultService {

	override fun list(lawFirmId: String, pageable: Pageable): Page<Consult> {
		return consultRepository.findByLawFirmId(lawFirmId, pageable)
	}

	override fun findByCompanyId(companyId: String): List<Consult> {
		return consultRepository.findByCompanyId(companyId)
	}
}