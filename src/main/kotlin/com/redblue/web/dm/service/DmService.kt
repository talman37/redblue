package com.redblue.web.dm.service

import com.redblue.web.dm.model.Dm
import com.redblue.web.dm.model.DmHistory
import java.util.*

interface DmService {

	fun list(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?): List<DmHistory>

	fun findByLawFirmId(lawFirmId: String): List<Dm>

	fun saveHistory(dmHistories: List<DmHistory>)

}