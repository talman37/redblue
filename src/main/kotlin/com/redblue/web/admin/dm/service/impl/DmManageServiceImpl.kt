package com.redblue.web.admin.dm.service.impl

import com.redblue.web.admin.dm.service.DmManageService
import com.redblue.web.dm.model.Dm
import com.redblue.web.dm.repository.DmRepository
import org.springframework.stereotype.Service

@Service
class DmManageServiceImpl(
	private val dmRepository: DmRepository
) : DmManageService {
	override fun list(): List<Dm> {
		return dmRepository.findAll()
	}
}