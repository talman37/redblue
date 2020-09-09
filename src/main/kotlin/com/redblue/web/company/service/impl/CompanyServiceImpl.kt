package com.redblue.web.company.service.impl

import com.redblue.web.company.model.Company
import com.redblue.web.company.repository.CompanyRepository
import com.redblue.web.company.repository.ExecutiveRepository
import com.redblue.web.company.repository.StockRepository
import com.redblue.web.company.service.CompanyService
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl(
	private val companyRepository: CompanyRepository,
	private val stockRepository: StockRepository,
	private val executiveRepository: ExecutiveRepository
): CompanyService {
	override fun list(lawFirmId: String): List<Company> {
		return companyRepository.findByLawFirmId(lawFirmId)
	}

	override fun search(lawFirmId: String, q: String):Company {
		return companyRepository.search(lawFirmId, q)
	}

	override fun detail(id: String): Company {
		val company = companyRepository.findById(id).get()
		company.stock = stockRepository.findByCompanyId(id)
		company.executives = executiveRepository.findByCompanyId(id)
		return company
	}
}