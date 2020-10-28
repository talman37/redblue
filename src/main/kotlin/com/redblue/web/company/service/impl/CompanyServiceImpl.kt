package com.redblue.web.company.service.impl

import com.redblue.web.company.model.Company
import com.redblue.web.company.model.CompanyMasterHistory
import com.redblue.web.company.model.IssuedType
import com.redblue.web.company.model.dto.CompanyMasterUpdateDto
import com.redblue.web.company.repository.*
import com.redblue.web.company.service.CompanyService
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class CompanyServiceImpl(
	private val companyRepository: CompanyRepository,
	private val stockRepository: StockRepository,
	private val executiveRepository: ExecutiveRepository,
	private val stockholderRepository: StockholderRepository,
	private val masterHistoryRepository: CompanyMasterHistoryRepository
): CompanyService {
	override fun list(lawFirmId: String, q: String?): List<Company> {
		return companyRepository.findByLawFirmId(lawFirmId, q)
	}

	override fun count(lawFirmId: String): Int {
		return companyRepository.countBylawFirmId(lawFirmId)
	}

	override fun search(lawFirmId: String, q: String):Company {
		return companyRepository.search(lawFirmId, q)
	}

	override fun detail(id: String): Company {
		val company = companyRepository.findById(id).get()
		company.stock = stockRepository.findByCompanyId(id)
		company.stockholders = stockholderRepository.findByCompanyId(id)
		return company
	}

	override fun save(company: Company) {
		companyRepository.save(company)

		company.stock?.let {
			val stock = company.stock!!.copy(
				companyId = company.id
			)
			stockRepository.save(stock)
		}

		masterHistoryRepository.save(
			CompanyMasterHistory(
				type = IssuedType.CREATED,
				companyId = company.id,
				registerNumber = company.registerNumber,
				registerOffice = company.registerOffice,
				companyNumber1 = company.companyNumber1,
				companyNumber2 = company.companyNumber2,
				companyName = company.companyName,
				companyDivision = company.companyDivision,
				companyManageNumber = company.companyManageNumber,
				companyManageState = company.companyManageState,
				companyState = company.companyState,
				displayCompanyType = company.displayCompanyType,
				companySubName = company.companySubName,
				recommender = company.recommender
			)
		)
	}

	override fun updateCompanyMaster(id: String, dto: CompanyMasterUpdateDto) {
		val company = companyRepository.findById(id)
		if(!company.isPresent) {
			throw Exception("company is not exist.")
		}
		val updateCompany = company.get().copy(
			registerNumber = dto.registerNumber,
			registerOffice = dto.registerOffice,
			companyNumber1 = dto.companyNumber1,
			companyNumber2 = dto.companyNumber2,
			companyName = dto.companyName,
			companyDivision = dto.companyDivision,
			companyManageNumber = dto.companyManageNumber,
			companyManageState = dto.companyManageState,
			companyState = dto.companyState,
			displayCompanyType = dto.displayCompanyType,
			companySubName = dto.companySubName,
			recommender = dto.recommender,
			companyUpdatedAt = if(company.get().companyName != dto.companyName){
				Date()
			} else {
				company.get().companyUpdatedAt
			}
		)
		companyRepository.save(updateCompany)

		masterHistoryRepository.save(
			CompanyMasterHistory(
				type = IssuedType.UPDATED,
				companyId = updateCompany.id,
				registerNumber = updateCompany.registerNumber,
				registerOffice = updateCompany.registerOffice,
				companyNumber1 = updateCompany.companyNumber1,
				companyNumber2 = updateCompany.companyNumber2,
				companyName = updateCompany.companyName,
				companyDivision = updateCompany.companyDivision,
				companyManageNumber = updateCompany.companyManageNumber,
				companyManageState = updateCompany.companyManageState,
				companyState = updateCompany.companyState,
				displayCompanyType = updateCompany.displayCompanyType,
				companySubName = updateCompany.companySubName,
				recommender = updateCompany.recommender
			)
		)
	}
}