package com.redblue.web.company.service.impl

import com.redblue.web.company.model.*
import com.redblue.web.company.model.dto.CompanyMasterUpdateDto
import com.redblue.web.company.model.dto.CompanyNoticeWayUpdateDto
import com.redblue.web.company.model.dto.CompanySubUpdateDto
import com.redblue.web.company.model.dto.SummaryResponseDto
import com.redblue.web.company.repository.*
import com.redblue.web.company.service.CompanyService
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*
import javax.transaction.Transactional

@Service
class CompanyServiceImpl(
	private val companyRepository: CompanyRepository,
	private val companyBranchRepository: CompanyBranchRepository,
	private val executiveRepository: ExecutiveRepository,
	private val stockRepository: StockRepository,
	private val stockholderRepository: StockholderRepository,
	private val contactRepository: ContactRepository,
	private val masterHistoryRepository: CompanyMasterHistoryRepository,
	private val subHistoryRepository: CompanySubHistoryRepository,
	private val stockHistoryRepository: StockHistoryRepository,
	private val purposeDetailRepository: PurposeDetailRepository,
	private val executiveHistoryRepository: ExecutiveHistoryRepository
): CompanyService {

	val positions = mutableListOf("대표이사", "공동대표이사", "사내이사", "사외이사", "기타비상무이사", "감사", "감사위원", "대표집행임원", "집행임원", "주주")

	override fun list(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, companyState: MutableList<String>, searchType: String?, positionTarget: String?, modifiedStartDate: Date?, modifiedEndDate: Date?): List<Company> {
		return companyRepository.findByLawFirmId(lawFirmId, q, startDate, endDate, companyState, searchType, positionTarget, modifiedStartDate, modifiedEndDate)
	}

	override fun listExcel(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, companyState: MutableList<String>, searchType: String?, positionTarget: String?, modifiedStartDate: Date?, modifiedEndDate: Date?): List<Company> {
		val companies = companyRepository.findByLawFirmId(lawFirmId, q, startDate, endDate, companyState, searchType, positionTarget, modifiedStartDate, modifiedEndDate)
		val companyIds = companies.map { it.id }
		val stocks = stockRepository.findByCompanyIdIn(companyIds)
		val executives = executiveRepository.findByCompanyIdIn(companyIds)
		val executiveMap = executives.groupBy { it.companyId }
		val contacts = contactRepository.findByCompanyIdIn(companyIds)
		val contactMap = contacts.groupBy { it.companyId }
		val purposeDetails = purposeDetailRepository.findByCompanyIdIn(companyIds)
		val purposeMap = purposeDetails.groupBy { it.companyId }
		for (company in companies) {
			loop1@ for (stock in stocks) {
				if(company.id == stock.companyId) {
					company.stock = stock
					break@loop1
				}
			}
			loop2@ for (entry in purposeMap.entries) {
				if(company.id == entry.key) {
					company.purposeDetail = entry.value
					break@loop2
				}
			}

			loop3@ for (entry in contactMap.entries) {
				if(company.id == entry.key) {
					company.contacts = entry.value as MutableList<Contact>
					break@loop3
				}
			}

			loop4@ for (entry in executiveMap.entries) {
				if(company.id == entry.key) {
					company.executives = entry.value as MutableList<Executive>
					break@loop4
				}
			}
		}

		return companies
	}

	override fun listDm(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, companyState: MutableList<String>, searchType: String?, positionTarget: String?, modifiedStartDate: Date?, modifiedEndDate: Date?): List<Company> {
		val companies = companyRepository.findByLawFirmId(lawFirmId, q, startDate, endDate, companyState, searchType, positionTarget, modifiedStartDate, modifiedEndDate)
		val companyIds = companies.map { it.id }
		val executives = executiveRepository.findByCompanyIdIn(companyIds)
		val executiveMap = executives.groupBy { it.companyId }
		for (company in companies) {
			loop1@ for (entry in executiveMap.entries) {
				if(company.id == entry.key) {
					company.executives = entry.value as MutableList<Executive>
					break@loop1
				}
			}
		}
		return companies
	}

	override fun findExecutivesByCompanyId(companyId: String): List<Executive> {
		return executiveRepository.findByCompanyIdOrderByExpiredAt(companyId)
	}

	override fun totalCount(lawFirmId: String): Int {
		return companyRepository.countBylawFirmId(lawFirmId)
	}

	override fun manageCount(lawFirmId: String): Int {
		return companyRepository.countBylawFirmIdAndCompanyState(lawFirmId, "유효법인")
	}

	override fun findByName(lawFirmId: String, name: String):List<Company> {
		return companyRepository.findByLawFirmIdAndCompanyName(lawFirmId, name)
	}

	override fun getSummary(companyId: String): SummaryResponseDto {
		val company = companyRepository.findById(companyId)
		val executives = executiveRepository.findByCompanyIdOrderByExpiredAt(companyId)

		val sortedExecutives = mutableListOf<Executive>()
		for (position in positions) {
			for (executive in executives) {
				if(position == executive.position) {
					sortedExecutives.add(executive)
				}
			}
		}

		return SummaryResponseDto.of(
			sortedExecutives,
			contactRepository.findByCompanyId(companyId),
			company.get().precautions
		)
	}

	override fun duplicateCheck(companyNumber1: String, companyNumber2: String): Boolean {
		return companyRepository.existsByCompanyNumber1AndCompanyNumber2(companyNumber1, companyNumber2)
	}

	@Transactional
	override fun delete(id: String) {
		executiveHistoryRepository.deleteByCompanyId(id)
		stockHistoryRepository.deleteByCompanyId(id)
		subHistoryRepository.deleteByCompanyId(id)
		masterHistoryRepository.deleteByCompanyId(id)

		purposeDetailRepository.deleteByCompanyId(id)
		contactRepository.deleteByCompanyId(id)
		companyBranchRepository.deleteByCompanyId(id)
		executiveRepository.deleteByCompanyId(id)
		stockRepository.deleteByCompanyId(id)

		companyRepository.deleteById(id)
	}

	override fun detail(id: String): Company {
		val company = companyRepository.findById(id).get()
		company.branches = companyBranchRepository.findByCompanyId(id)

		val executives = executiveRepository.findByCompanyIdOrderByExpiredAt(id).toMutableList()

		val sortedExecutives = mutableListOf<Executive>()

		for (position in positions) {
			for (executive in executives) {
				if(position == executive.position) {
					sortedExecutives.add(executive)
				}
			}
		}

		company.executives = sortedExecutives
		company.stock = stockRepository.findByCompanyId(id)
		company.stockholders = stockholderRepository.findByCompanyId(id)
		company.purposeDetail = purposeDetailRepository.findByCompanyId(id)
		company.contacts = contactRepository.findByCompanyId(id)
		return company
	}

	override fun save(company: Company) {
		if(company.executives != null) {
			for (executive in company.executives!!) {
				if(executive.nationality == "기타") {
					executive.nationality = executive.countryValue
				}
			}
		}

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
		company.executives?.let {
			executiveRepository.saveAll(company.executives!!)
		}

		executiveHistoryRepository.save(ExecutiveHistory(
			type = IssuedType.CREATED,
			companyId = company.id,
			data = company.executives ?: emptyList()
		))

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
			purposeDetailRepository.saveAll(company.purposeDetail!!)
		}

		company.branches?.let {
			companyBranchRepository.saveAll(company.branches!!)
		}



	}

	override fun updateCompanyMaster(id: String, dto: CompanyMasterUpdateDto) {
		val company = companyRepository.findById(id)
		if(!company.isPresent) {
			throw Exception("company is not exist.")
		}
		val current = Date()
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
			precautions = dto.precautions,
			companyFormationAt = dto.companyFormationAt,
			companyUpdatedAt = if(company.get().companyName != dto.companyName){
				current
			} else {
				company.get().companyUpdatedAt
			}
		)
		updateCompany.updatedAt = current
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
		var current = Date()
		val updateCompany = company.get().copy(
			companyAddress = dto.companyAddress,
			companyPostalCode = dto.companyPostalCode,
			companyAddressUpdatedAt = if(company.get().companyAddress != dto.companyAddress){
				current
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
		updateCompany.updatedAt = current
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
			amount = dto.stock?.amount ?: 0,
			scheduleCount = dto.stock?.scheduleCount ?: 0,
			scheduleCountUpdatedAt = if(stock.scheduleCount != dto.stock?.scheduleCount) {
				current
			} else {
				stock.scheduleCountUpdatedAt
			},
			issuedCount = dto.stock?.issuedCount ?: 0,
			issuedCountUpdatedAt = if(stock.issuedCount != dto.stock?.issuedCount) {
				current
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

	override fun getHistories(id: String): CompanyHistory {
		return CompanyHistory(
			master = masterHistoryRepository.findByCompanyId(id),
			sub = subHistoryRepository.findByCompanyId(id),
			stock = stockHistoryRepository.findByCompanyId(id),
			executives = executiveHistoryRepository.findByCompanyId(id)
		)
	}

	@Transactional
	override fun saveBranches(companyId: String, branches: List<CompanyBranch>) {
		companyBranchRepository.deleteByCompanyId(companyId)
		if(branches.isNotEmpty()) {
			companyBranchRepository.saveAll(branches)
		}
		companyRepository.saveUpdatedAt(companyId, Date())
	}

	@Transactional
	override fun saveContacts(companyId: String, contacts: List<Contact>) {
		contactRepository.deleteByCompanyId(companyId)
		if(contacts.isNotEmpty()) {
			contactRepository.saveAll(contacts)
		}
		companyRepository.saveUpdatedAt(companyId, Date())
	}

	@Transactional
	override fun saveExecutives(companyId: String, executives: List<Executive>) {
		executiveRepository.deleteByCompanyId(companyId)
		if(executives.isNotEmpty()) {
			for (executive in executives) {
				if(executive.nationality == "기타") {
					executive.nationality = executive.countryValue
				}
			}
			executiveRepository.saveAll(executives)
			executiveHistoryRepository.save(ExecutiveHistory(
				type = IssuedType.UPDATED,
				companyId = companyId,
				data = executives
			))
		}
		companyRepository.saveUpdatedAt(companyId, Date())
	}

	@Transactional
	override fun saveStockHolders(companyId: String, stockHolders: List<Stockholder>) {
		stockholderRepository.deleteByCompanyId(companyId)
		if(stockHolders.isNotEmpty()) {
			stockholderRepository.saveAll(stockHolders)
		}
	}

	@Transactional
	override fun savePurposeDetail(companyId: String, purposeDetails: List<PurposeDetail>) {
		purposeDetailRepository.deleteByCompanyId(companyId)
		if(purposeDetails.isNotEmpty()) {
			purposeDetailRepository.saveAll(purposeDetails)
		}
		companyRepository.saveUpdatedAt(companyId, Date())
	}

	override fun updateNoticeWay(id: String, dto: CompanyNoticeWayUpdateDto) {
		val company = companyRepository.findById(id)
		val current = Date()
		val updateCompany = company.get().copy(
			noticeWay = dto.noticeWay,
			noticeWayUpdatedAt = current,
			updatedAt = current
		)
		companyRepository.save(updateCompany)
	}
}