package com.redblue.web.company.service.impl

import com.redblue.web.company.model.Company
import com.redblue.web.company.repository.CompanyRepository
import com.redblue.web.company.service.CompanyService
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl(
	private val companyRepository: CompanyRepository
): CompanyService {
	override fun list(lawFirmId: String): List<Company> {
		return companyRepository.findByLawFirmId(lawFirmId)
	}
}