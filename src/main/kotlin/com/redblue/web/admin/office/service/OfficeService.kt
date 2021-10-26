package com.redblue.web.admin.office.service

import com.redblue.web.admin.office.model.dto.LawFirmUpdateRequestDto
import com.redblue.web.lawfirm.model.LawFirm

interface OfficeService {

	fun findAll(): List<LawFirm>

	fun save(lawFirm: LawFirm)

	fun details(id: String): LawFirm

	fun update(request: LawFirmUpdateRequestDto)

}