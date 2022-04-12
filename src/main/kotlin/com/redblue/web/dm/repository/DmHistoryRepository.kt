package com.redblue.web.dm.repository

import com.redblue.web.dm.model.DmHistory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DmHistoryRepository: DmHistoryQueryDslRepository, JpaRepository<DmHistory, String>{

}

interface DmHistoryQueryDslRepository {

	fun findHistory(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, pageable: Pageable): Page<DmHistory>

}