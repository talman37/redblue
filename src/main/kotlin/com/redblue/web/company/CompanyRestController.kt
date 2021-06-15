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
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat

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
	): ResponseEntity<Void> {
		companyService.updateCompanyMaster(id, dto)
		return ResponseEntity(HttpStatus.OK)
	}

	@PatchMapping("/{id}/sub")
	fun updateCompanySub(
		@PathVariable("id") id: String,
		@RequestBody dto: CompanySubUpdateDto,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<Void> {
		companyService.updateCompanySub(id, dto)
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
	): ResponseEntity<Void> {
		companyService.saveExecutives(id, dto.to(id))
		return ResponseEntity(HttpStatus.OK)
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

	@GetMapping("/download/corporations.xlsx")
	fun downloadExcel(
		@RequestParam(value = "q", required = false) searchValue: String?,
		@RequestParam(value = "start", required = false) startDate: String?,
		@RequestParam(value = "end", required = false) endDate: String?,
		@RequestParam(value = "state", required = false) state: MutableList<String>?,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<InputStreamResource> {

		val searchValue = searchValue?.let {
			if (it.isEmpty()) {
				null
			} else {
				searchValue
			}
		}

		val start = startDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(startDate)
			}
		}

		val end = endDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(endDate)
			}
		}

		val companyState: MutableList<String> = if(state.isNullOrEmpty()) {
			mutableListOf("신규법인", "관리법인", "안내후미등기")
		} else {
			state
		}

		val companies = companyService.list(user.lawFirmId, searchValue, start, end, companyState)
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
		@CurrentUser user: LawFirmUser
	): ResponseEntity<InputStreamResource> {

		val searchValue = searchValue?.let {
			if (it.isEmpty()) {
				null
			} else {
				searchValue
			}
		}

		val start = startDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(startDate)
			}
		}

		val end = endDate?.let {
			if (it.isEmpty()) {
				null
			} else {
				SimpleDateFormat("yyyy-MM-dd").parse(endDate)
			}
		}

		val companyState: MutableList<String> = if(state.isNullOrEmpty()) {
			mutableListOf("신규법인", "관리법인", "안내후미등기")
		} else {
			state
		}

		val companies = companyService.list(user.lawFirmId, searchValue, start, end, companyState)
		val resource = companyDmService.generate(companies, user)
		val headers = HttpHeaders()
		headers.add("Content-Disposition", "attachment; filename=dm.pdf")

		return ResponseEntity.ok().headers(headers).body(InputStreamResource(resource))

	}



}