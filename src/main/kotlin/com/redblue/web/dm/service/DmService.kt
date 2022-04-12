package com.redblue.web.dm.service

import com.redblue.web.dm.model.Dm
import com.redblue.web.dm.model.DmHistory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface DmService {

	fun list(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, pageable: Pageable): Page<DmHistory>

	fun findByLawFirmId(lawFirmId: String): List<Dm>

	fun saveHistory(dmHistories: List<DmHistory>)

	fun details(id: Int): Dm

}