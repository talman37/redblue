package com.redblue.web.dm.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.redblue.web.company.model.Executive
import com.redblue.web.dm.model.Dm
import com.redblue.web.dm.model.DmHistory
import com.redblue.web.dm.repository.DmHistoryRepository
import com.redblue.web.dm.repository.DmRepository
import com.redblue.web.dm.service.DmService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class DmServiceImpl(
	private val historyRepo: DmHistoryRepository,
	private val dmRepo: DmRepository
) : DmService {

	override fun list(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, pageable: Pageable): Page<DmHistory> {
		val histories = historyRepo.findHistory(lawFirmId, q, startDate, endDate, pageable)
		for (history in histories) {
			val executives = mutableListOf<Executive>()
			history.content.let {
				var exes : Array<Executive> = ObjectMapper().readValue(it, Array<Executive>::class.java)
				executives.addAll(exes)
			}
			history.executives = executives
		}
		return histories
	}

	override fun findByLawFirmId(lawFirmId: String): List<Dm> {
		return dmRepo.findByLawFirmId(lawFirmId)
	}

	override fun saveHistory(dmHistories: List<DmHistory>) {
		historyRepo.saveAll(dmHistories)
	}

	override fun details(id: Int): Dm {
		return dmRepo.findById(id).get()
	}
}