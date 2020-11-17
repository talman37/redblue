package com.redblue.web.company.service.impl

import com.redblue.web.company.model.*
import com.redblue.web.company.model.dto.CompanyMasterUpdateDto
import com.redblue.web.company.model.dto.CompanySubUpdateDto
import com.redblue.web.company.repository.*
import com.redblue.web.company.service.CompanyService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class CompanyServiceImpl(
	private val companyRepository: CompanyRepository,
	private val stockRepository: StockRepository,
	private val stockholderRepository: StockholderRepository,
	private val contactRepository: ContactRepository,
	private val masterHistoryRepository: CompanyMasterHistoryRepository,
	private val subHistoryRepository: CompanySubHistoryRepository,
	private val stockHistoryRepository: StockHistoryRepository,
	private val purposeDetailRepository: PurposeDetailRepository
): CompanyService {
	override fun list(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, pageable: Pageable): Page<Company> {
		return companyRepository.findByLawFirmId(lawFirmId, q, startDate, endDate, pageable)
	}

	override fun count(lawFirmId: String): Int {
		return companyRepository.countBylawFirmId(lawFirmId)
	}

	override fun findByName(lawFirmId: String, name: String):List<Company> {
		return companyRepository.findByLawFirmIdAndCompanyName(lawFirmId, name)
	}

	override fun detail(id: String): Company {
		val company = companyRepository.findById(id).get()
		company.stock = stockRepository.findByCompanyId(id)
		company.stockholders = stockholderRepository.findByCompanyId(id)
		return company
	}

	override fun save(company: Company) {
		companyRepository.save(company)

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

		subHistoryRepository.save(
			CompanySubHistory(
				type = IssuedType.CREATED,
				companyId = company.id,
				companyAddress = company.companyAddress,
				companyPostalCode = company.companyPostalCode,
				deliveryPlace = company.deliveryPlace,
				deliveryPlacePostalCode = company.deliveryPlacePostalCode,
				businessNumber = company.businessNumber,
				businessType = company.businessType,
				businessCondition = company.businessCondition,
				settlementMonth = company.settlementMonth
			)
		)

		company.stock?.let {
			val stock = company.stock!!.copy(
				companyId = company.id
			)
			stockRepository.save(stock)

			stockHistoryRepository.save(
				StockHistory(
					type = IssuedType.CREATED,
					companyId = company.stock?.companyId,
					stockId = company.stock?.id,
					amount = company.stock?.amount,
					scheduleCount = company.stock?.scheduleCount,
					issuedCount = company.stock?.issuedCount
				)
			)
		}

		company.contacts?.let { contacts ->
			contacts.forEach {
				it.companyId = company.id
			}
			contactRepository.saveAll(contacts)
		}

		company.purposeDetail?.let {
			val purposeDetail = company.purposeDetail!!.copy(
				companyId = company.id,
				detailUpdatedAt = Date()
			)
			purposeDetailRepository.save(purposeDetail)
		}



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

	override fun updateCompanySub(id: String, dto: CompanySubUpdateDto) {
		val company = companyRepository.findById(id)
		if(!company.isPresent) {
			throw Exception("company is not exist.")
		}

		val updateCompany = company.get().copy(
			companyAddress = dto.companyAddress,
			companyPostalCode = dto.companyPostalCode,
			companyAddressUpdatedAt = if(company.get().companyAddress != dto.companyAddress){
				Date()
			} else {
				company.get().companyUpdatedAt
			},
			deliveryPlace = dto.deliveryPlace,
			deliveryPlacePostalCode = dto.deliveryPlacePostalCode,
			businessCondition = dto.businessNumber,
			businessType = dto.businessType,
			businessNumber = dto.businessNumber,
			settlementMonth = dto.settlementMonth
		)
		companyRepository.save(updateCompany)

		subHistoryRepository.save(
			CompanySubHistory(
				type = IssuedType.UPDATED,
				companyId = updateCompany.id,
				companyAddress = updateCompany.companyAddress,
				companyPostalCode = updateCompany.companyPostalCode,
				deliveryPlace = updateCompany.deliveryPlace,
				deliveryPlacePostalCode = updateCompany.deliveryPlacePostalCode,
				businessNumber = updateCompany.businessNumber,
				businessType = updateCompany.businessType,
				businessCondition = updateCompany.businessCondition,
				settlementMonth = updateCompany.settlementMonth
			)
		)

		val stock = stockRepository.findByCompanyId(id)

		val updateStock = stock?.copy(
			amount = dto.stock?.amount,
			scheduleCount = dto.stock?.scheduleCount,
			scheduleCountUpdatedAt = if(stock.scheduleCount != dto.stock?.scheduleCount) {
				Date()
			} else {
				stock.scheduleCountUpdatedAt
			},
			issuedCount = dto.stock?.issuedCount,
			issuedCountUpdatedAt = if(stock.issuedCount != dto.stock?.issuedCount) {
				Date()
			} else {
				stock.issuedCountUpdatedAt
			}
		)
		stockRepository.save(updateStock!!)

		stockHistoryRepository.save(
			StockHistory(
				type = IssuedType.UPDATED,
				companyId = updateStock.companyId,
				stockId = updateStock.id,
				amount = updateStock.amount,
				scheduleCount = updateStock.scheduleCount,
				issuedCount = updateStock.issuedCount
			)
		)
	}

	override fun saveContact(contact: Contact) {
		contactRepository.save(contact)
	}
}