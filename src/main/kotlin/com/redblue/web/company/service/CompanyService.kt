package com.redblue.web.company.service

import com.redblue.web.company.model.Company

interface CompanyService {

	fun list(lawFirmId: String): List<Company>

	fun search(lawFirmId: String, q: String): Company

	fun detail(id: String): Company
}