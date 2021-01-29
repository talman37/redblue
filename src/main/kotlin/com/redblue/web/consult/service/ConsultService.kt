package com.redblue.web.consult.service

import com.redblue.web.consult.model.Consult
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface ConsultService {

	fun list(lawFirmId: String, searchValue:String?, startDate: Date?, endDate: Date?, progress: List<Consult.Progress>?, pageable: Pageable): Page<Consult>

	fun findByCompanyId(companyId: String): List<Consult>

	fun detail(id: String): Consult

	fun save(consult: Consult)

}