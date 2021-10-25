package com.redblue.web.company

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.company.model.dto.*
import com.redblue.web.company.service.CompanyDmService
import com.redblue.web.company.service.CompanyExcelService
import com.redblue.web.company.service.CompanyService
import com.redblue.web.consult.model.Consult
import com.redblue.web.consult.service.ConsultService
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.util.*

@RestController
@RequestMapping("/v1/companies")
class CompanyRestController(
	private val companyService: CompanyService,
	private val companyExcelService: CompanyExcelService,
	private val companyDmService: CompanyDmService,
	private val consultService: ConsultService
) {

	@GetMapping
	fun search(
		@RequestParam("name") name: String,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<List<CompanySearchResponseDto>> {
		val companies = companyService.findByName(user.lawFirmId, name)
		return ResponseEntity.ok(CompanySearchResponseDto.of(companies))
	}

	@GetMapping("/{id}/summary")
	fun getSummary(
		@PathVariable("id") id: String,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<SummaryResponseDto> {
		return ResponseEntity.ok(companyService.getSummary(id))
	}

	@PostMapping
	fun save(
		@RequestBody dto: CompanyCreateDto,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<Void> {
		companyService.save(dto.to(user.lawFirmId))
		return ResponseEntity(HttpStatus.OK)
	}

	@PatchMapping("/{id}/master")
	fun updateCompanyMaster(
		@PathVariable("id") id: String,
		@RequestBody dto: CompanyMasterUpdateDto,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<String> {
		val updatedAt = companyService.updateCompanyMaster(id, dto)
		return ResponseEntity.ok(SimpleDateFormat("yyyy-MM-dd").format(updatedAt))
	}

	@PatchMapping("/{id}/sub")
	fun updateCompanySub(
		@PathVariable("id") id: String,
		@RequestBody dto: CompanySubUpdateDto,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<String> {
		val updatedAt = companyService.updateCompanySub(id, dto)
		return ResponseEntity.ok(SimpleDateFormat("yyyy-MM-dd").format(updatedAt))
	}

	@PatchMapping("/{id}/notice-way")
	fun updateNoticeWay(
		@PathVariable("id") id: String,
		@RequestBody dto: CompanyNoticeWayUpdateDto,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<Void> {
		companyService.updateNoticeWay(id, dto)
		return ResponseEntity(HttpStatus.OK)
	}

	@PostMapping("/{id}/branches")
	fun updateCompanyBranch(
		@PathVariable("id") id: String,
		@RequestBody dto: CompanyBranchUpdateDto,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<Void> {
		companyService.saveBranches(id, dto.to(id))
		return ResponseEntity(HttpStatus.OK)
	}

	@PostMapping("/{id}/contacts")
	fun updateContact(
		@PathVariable("id") id: String,
		@RequestBody dto: ContactUpdateDto,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<Void> {
		companyService.saveContacts(id, dto.to(id))
		return ResponseEntity(HttpStatus.OK)
	}

	@PostMapping("/{id}/executives")
	fun updateExecutive(
		@PathVariable("id") id: String,
		@RequestBody dto: ExecutiveUpdateDto,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<String> {
		val updatedAt = companyService.saveExecutives(id, dto.to(id))
		return ResponseEntity.ok(SimpleDateFormat("yyyy-MM-dd").format(updatedAt))
	}

	@PostMapping("/{id}/stockholders")
	fun updateStockHolder(
		@PathVariable("id") id: String,
		@RequestBody dto: StockHolderUpdateDto,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<Void> {
		companyService.saveStockHolders(id, dto.to(id))
		return ResponseEntity(HttpStatus.OK)
	}

	@PostMapping("/{id}/purpose-details")
	fun updatePurposeDetail(
		@PathVariable("id") id: String,
		@RequestBody dto: CompanyPurposeDetailsUpdateDto,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<Void> {
		companyService.savePurposeDetail(id, dto.to(id))
		return ResponseEntity(HttpStatus.OK)
	}

	@PostMapping("/{id}/consults")
	fun updateConsult(
		@PathVariable("id") id: String,
		@RequestBody consult: Consult,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<Void> {
		consultService.save(consult)
		return ResponseEntity(HttpStatus.OK)
	}

	@GetMapping("/duplicate-check")
	fun duplicateCheck(
		@RequestParam("registerNumber") registerNumber: Int,
		@RequestParam("registerOffice") registerOffice: String,
		@RequestParam("companyDivision") companyDivision: String
	): ResponseEntity<Boolean> {
		return ResponseEntity.ok(companyService.duplicateCheck(registerNumber, registerOffice, companyDivision))
	}

	@DeleteMapping("/{id}")
	fun delete(
		@PathVariable("id") id: String
	): ResponseEntity<Void> {
		companyService.delete(id)
		return ResponseEntity(HttpStatus.OK)
	}

	@GetMapping("/download/corporations.xlsx")
	fun downloadExcel(
		@RequestParam(value = "q", required = false) searchValue: String?,
		@RequestParam(value = "start", required = false) startDate: String?,
		@RequestParam(value = "end", required = false) endDate: String?,
		@RequestParam(value = "state", required = false) state: MutableList<String>?,
		@RequestParam(value = "searchType", required = false) searchType: String?,
		@RequestParam(value = "positionTarget", required = false) positionTarget: String?,
		@RequestParam(value = "modifiedStartDate", required = false) modifiedStartDate: String?,
		@RequestParam(value = "modifiedEndDate", required = false) modifiedEndDate: String?,
		@RequestParam(value = "searchRange", required = false) searchRange: String?,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<InputStreamResource> {

		val searchValue = searchValue?.let {
			if (it.isEmpty()) {
				null
			} else {
				searchValue
			}
		}

		var start = startDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(startDate)
			}
		}

		var end = endDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(endDate)
			}
		}

		val updatedStart = modifiedStartDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(startDate)
			}
		}

		val updatedEnd = modifiedEndDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(endDate)
			}
		}

		val companyState: MutableList<String> = state ?: mutableListOf()

		val companies = companyService.listExcel(user.lawFirmId, searchValue, start, end, companyState, searchType, positionTarget, updatedStart, updatedEnd, searchRange)
		val resource = companyExcelService.generate(companies)
		val headers = HttpHeaders()
		headers.add("Content-Disposition", "attachment; filename=corporations.xlsx")

		return ResponseEntity.ok().headers(headers).body(InputStreamResource(resource))

	}

	@GetMapping("/download/dm.pdf")
	fun downloadDm(
		@RequestParam(value = "q", required = false) searchValue: String?,
		@RequestParam(value = "start", required = false) startDate: String?,
		@RequestParam(value = "end", required = false) endDate: String?,
		@RequestParam(value = "state", required = false) state: MutableList<String>?,
		@RequestParam(value = "searchType", required = false) searchType: String?,
		@RequestParam(value = "positionTarget", required = false) positionTarget: String?,
		@RequestParam(value = "modifiedStartDate", required = false) modifiedStartDate: String?,
		@RequestParam(value = "modifiedEndDate", required = false) modifiedEndDate: String?,
		@RequestParam(value = "searchRange", required = false) searchRange: String?,
		@RequestParam(value = "templateId") templateId: Int,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<InputStreamResource> {

		val searchValue = searchValue?.let {
			if (it.isEmpty()) {
				null
			} else {
				searchValue
			}
		}

		var start = startDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(startDate)
			}
		}

		var end = endDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(endDate)
			}
		}

		val updatedStart = modifiedStartDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(startDate)
			}
		}

		val updatedEnd = modifiedEndDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(endDate)
			}
		}

		val companyState: MutableList<String> = state ?: mutableListOf()

		val companies = companyService.listDm(user.lawFirmId, searchValue, start, end, companyState, searchType, positionTarget, updatedStart, updatedEnd, searchRange)
		val resource = companyDmService.generate(companies, user, templateId, start, end)
		val headers = HttpHeaders()
		headers.add("Content-Disposition", "attachment; filename=dm.pdf")

		return ResponseEntity.ok().headers(headers).body(InputStreamResource(resource))

	}



}