package com.redblue.web.company

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.company.model.dto.CompanyCreateDto
import com.redblue.web.company.model.dto.CompanyMasterUpdateDto
import com.redblue.web.company.model.dto.CompanySearchResponseDto
import com.redblue.web.company.model.dto.CompanySubUpdateDto
import com.redblue.web.company.service.CompanyDmService
import com.redblue.web.company.service.CompanyExcelService
import com.redblue.web.company.service.CompanyService
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
	private val companyDmService: CompanyDmService
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
		companyService.save(dto.of(user.lawFirmId))
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

	@PostMapping("/{id}/contact")
	fun addContact(
		@PathVariable("id") id: String,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<Void> {
		return ResponseEntity(HttpStatus.OK)
	}

	@PostMapping("/{id}/executive")
	fun addExecutive(
		@PathVariable("id") id: String,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<Void> {
		return ResponseEntity(HttpStatus.OK)
	}

	@PostMapping("/{id}/stock-holder")
	fun addStockHolder(
		@PathVariable("id") id: String,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<Void> {
		return ResponseEntity(HttpStatus.OK)
	}

	@PostMapping("/{id}/purpose")
	fun savePurposeDetail(
		@PathVariable("id") id: String,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<Void> {
		return ResponseEntity(HttpStatus.OK)
	}

	@PostMapping("/{id}/consults")
	fun addConsult(
		@PathVariable("id") id: String,
		@CurrentUser user: LawFirmUser
	): ResponseEntity<Void> {
		return ResponseEntity(HttpStatus.OK)
	}

	@GetMapping("/download/corporations.xlsx")
	fun downloadExcel(
		@RequestParam(value = "q", required = false) searchValue: String?,
		@RequestParam(value = "start", required = false) startDate: String?,
		@RequestParam(value = "end", required = false) endDate: String?,
		@CurrentUser user: LawFirmUser,
		@RequestParam("page", required = false, defaultValue = "1") page: String
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

		val pageSize = 100

		val companies = companyService.list(user.lawFirmId, searchValue, start, end, PageRequest.of(Integer.valueOf(page) - 1, pageSize))
		val resource = companyExcelService.generate(companies.content)
		val headers = HttpHeaders()
		headers.add("Content-Disposition", "attachment; filename=corporations.xlsx")

		return ResponseEntity.ok().headers(headers).body(InputStreamResource(resource))

	}

	@GetMapping("/download/dm.pdf")
	fun downloadDm(
		@RequestParam(value = "q", required = false) searchValue: String?,
		@RequestParam(value = "start", required = false) startDate: String?,
		@RequestParam(value = "end", required = false) endDate: String?,
		@CurrentUser user: LawFirmUser,
		@RequestParam("page", required = false, defaultValue = "1") page: String
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

		val pageSize = 100

		val companies = companyService.list(user.lawFirmId, searchValue, start, end, PageRequest.of(Integer.valueOf(page) - 1, pageSize))
		val resource = companyDmService.generate(companies.content)
		val headers = HttpHeaders()
		headers.add("Content-Disposition", "attachment; filename=dm.pdf")

		return ResponseEntity.ok().headers(headers).body(InputStreamResource(resource))

	}



}