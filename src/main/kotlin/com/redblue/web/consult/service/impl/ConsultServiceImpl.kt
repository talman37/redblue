package com.redblue.web.consult.service.impl

import com.redblue.web.consult.model.Consult
import com.redblue.web.consult.repository.ConsultRepository
import com.redblue.web.consult.service.ConsultService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class ConsultServiceImpl(
	private val consultRepository: ConsultRepository
) : ConsultService {

	override fun list(lawFirmId: String, searchValue:String?, startDate: Date?, endDate: Date?, progress: List<Consult.Progress>?, pageable: Pageable): Page<Consult> {
		return consultRepository.findByLawFirmId(lawFirmId, searchValue, startDate, endDate, progress, pageable)
	}

	override fun findByCompanyId(companyId: String): List<Consult> {
		return consultRepository.findByCompanyId(companyId)
	}

	override fun detail(id: String): Consult {
		return consultRepository.details(id)
	}

	override fun save(consult: Consult) {
		consultRepository.save(consult)
	}

	override fun update(consult: Consult) {
		val savedConsult = this.detail(consult.id)
		consult.createdAt = savedConsult.createdAt
		consult.updatedAt = Date()
		consultRepository.save(consult)
	}

	override fun delete(id: String) {
		consultRepository.deleteById(id);
	}
}