package com.redblue.web.admin.office.service.impl

import com.redblue.web.admin.office.model.dto.LawFirmUpdateRequestDto
import com.redblue.web.admin.office.service.OfficeService
import com.redblue.web.lawfirm.model.LawFirm
import com.redblue.web.lawfirm.repository.LawFirmRepository
import org.springframework.stereotype.Service

@Service
class OfficeServiceImpl(
	private val lawFirmRepository: LawFirmRepository
): OfficeService {

	override fun findAll(): List<LawFirm> {
		return lawFirmRepository.findAll()
	}

	override fun save(lawFirm: LawFirm) {
		lawFirmRepository.save(lawFirm)
	}

	override fun details(id: String): LawFirm {
		return lawFirmRepository.findById(id).get()
	}

	override fun update(request: LawFirmUpdateRequestDto) {
		val lawFirm = this.details(request.id)
		lawFirm.name = request.name
		lawFirm.address = request.address
		lawFirm.postalCode = request.postalCode
		lawFirm.tel = request.tel
		lawFirm.fax = request.fax
		lawFirm.description = request.description
		lawFirm.etc = request.etc
		lawFirm.representative = request.representative
		lawFirmRepository.save(lawFirm)
	}
}