package com.redblue.web.consult.service

import com.redblue.web.consult.model.Consult

interface ConsultService {

	fun list(lawFirmId: String): List<Consult>

}