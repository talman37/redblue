package com.redblue.web.company.repository

import com.redblue.web.company.model.PurposeDetail
import org.springframework.data.jpa.repository.JpaRepository

interface PurposeDetailRepository: JpaRepository<PurposeDetail, String> {
	fun findByCompanyId(companyId: String): List<PurposeDetail>

	fun findByCompanyIdIn(ids: List<String>): List<PurposeDetail>

	fun deleteByCompanyId(companyId: String)

}