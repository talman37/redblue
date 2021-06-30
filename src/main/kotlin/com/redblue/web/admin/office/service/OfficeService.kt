package com.redblue.web.admin.office.service

import com.redblue.web.lawfirm.model.LawFirm

interface OfficeService {

	fun findAll(): List<LawFirm>

}