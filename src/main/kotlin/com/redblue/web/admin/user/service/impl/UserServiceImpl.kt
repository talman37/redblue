package com.redblue.web.admin.user.service.impl

import com.redblue.web.admin.user.service.UserService
import com.redblue.web.lawfirm.model.LawFirmUser
import com.redblue.web.lawfirm.repository.LawFirmUserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
	private val userRepo: LawFirmUserRepository
): UserService {

	override fun find(): List<LawFirmUser> {
		return userRepo.findAll()
	}

}