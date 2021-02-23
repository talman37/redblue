package com.redblue.web.company.repository

import com.redblue.web.company.model.CompanyBranch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyBranchRepository: JpaRepository<CompanyBranch, String> {

	fun findByCompanyId(companyId: String): List<CompanyBranch>

	fun deleteByCompanyId(companyId: String)

}