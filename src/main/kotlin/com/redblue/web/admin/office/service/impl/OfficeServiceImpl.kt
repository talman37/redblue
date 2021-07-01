package com.redblue.web.admin.office.service.impl

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
}