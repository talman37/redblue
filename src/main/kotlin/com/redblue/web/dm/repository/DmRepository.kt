package com.redblue.web.dm.repository

import com.redblue.web.dm.model.Dm
import org.springframework.data.jpa.repository.JpaRepository

interface DmRepository: DmQueryDslRepository, JpaRepository<Dm, Int> {
}


interface DmQueryDslRepository {

	fun findByLawFirmId(lawFirmId: String): List<Dm>

}