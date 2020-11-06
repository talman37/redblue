package com.redblue.web.company.service

import com.redblue.web.company.model.Company
import com.redblue.web.company.model.dto.CompanyMasterUpdateDto
import com.redblue.web.company.model.dto.CompanySubUpdateDto

interface CompanyService {

	fun list(lawFirmId: String, q: String?): List<Company>

	fun count(lawFirmId: String): Int

	fun search(lawFirmId: String, q: String): Company

	fun detail(id: String): Company

	fun save(company: Company)

	fun updateCompanyMaster(id: String, dto: CompanyMasterUpdateDto)

	fun updateCompanySub(id: String, dto: CompanySubUpdateDto)

}