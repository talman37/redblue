package com.redblue.web.lawfirm.repository

import com.redblue.web.lawfirm.model.RegisterOffice
import org.springframework.data.jpa.repository.JpaRepository

interface RegisterOfficeRepository: RegisterOfficeQueryDslRepository, JpaRepository<RegisterOffice, String> {

}

interface RegisterOfficeQueryDslRepository {
	fun find(searchValue: String?): List<RegisterOffice>
}