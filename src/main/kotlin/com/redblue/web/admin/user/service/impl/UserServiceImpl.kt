package com.redblue.web.admin.user.service.impl

import com.redblue.web.admin.user.model.dto.UserUpdateRequestDto
import com.redblue.web.admin.user.service.UserService
import com.redblue.web.lawfirm.model.LawFirmUser
import com.redblue.web.lawfirm.repository.LawFirmRepository
import com.redblue.web.lawfirm.repository.LawFirmUserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

@Service
class UserServiceImpl(
	private val userRepo: LawFirmUserRepository,
	private val lawFirmRepository: LawFirmRepository
): UserService {

	override fun find(): List<LawFirmUser> {
		val users = userRepo.findAll().filter { it.id != "ADMIN" }
		val lawsFirms = lawFirmRepository.findAll()
		for (user in users) {
			for (lawsFirm in lawsFirms) {
				if(user.lawFirmId == lawsFirm.id) {
					user.lawFirm = lawsFirm
				}
			}
		}
		return users
	}

	override fun save(lawFirmUser: LawFirmUser) {
		userRepo.save(lawFirmUser)
	}

	override fun detail(id: String): LawFirmUser {
		return userRepo.findById(id).get()
	}

	override fun update(request: UserUpdateRequestDto) {
		var user = this.detail(request.id)
		user.lawFirmId = request.lawFirmId
		user.name = request.name
		if(StringUtils.hasText(request.password)) {
			user.password = BCryptPasswordEncoder().encode(request.password)
		}
		userRepo.save(user)
	}

	override fun delete(id: String) {
		userRepo.deleteById(id)
	}
}