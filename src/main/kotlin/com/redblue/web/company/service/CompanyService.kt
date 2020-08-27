package com.redblue.web.company.service

import com.redblue.web.company.model.Company

interface CompanyService {

	fun list(lawFirmId: String): List<Company>

}