package com.redblue.web.admin.dm.service

import com.redblue.web.dm.model.Dm

interface DmManageService {

	fun list(): List<Dm>

}