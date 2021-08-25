package com.redblue.web.lawfirm.service

import com.redblue.web.lawfirm.model.LawFirmUserRegisterOffice
import com.redblue.web.lawfirm.model.RegisterOffice
import com.redblue.web.lawfirm.repository.LawFirmUserRegisterOfficeRepository
import com.redblue.web.lawfirm.repository.RegisterOfficeRepository
import org.springframework.stereotype.Service

@Service
class LawFirmService(

	private val registerOfficeRepository: RegisterOfficeRepository,

	private val lawFirmUserRegisterOfficeRepository: LawFirmUserRegisterOfficeRepository

) {
	fun findRegisterOffice(searchValue: String?): List<RegisterOffice> {
		return registerOfficeRepository.find(searchValue)
	}

	fun findUserRegisterOffice(userId: String): List<LawFirmUserRegisterOffice> {
		return lawFirmUserRegisterOfficeRepository.findByUserId(userId);
	}

}