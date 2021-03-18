package com.redblue.web.lawfirm.service

import com.redblue.security.core.userdetails.SecurityUser
import com.redblue.web.lawfirm.model.LawFirmUser
import com.redblue.web.lawfirm.repository.LawFirmRepository
import com.redblue.web.lawfirm.repository.LawFirmUserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class LawFirmUserService(
	private val lawFirmUserRepository: LawFirmUserRepository,
	private val lawFirmRepository: LawFirmRepository,
	private val passwordEncoder: PasswordEncoder
): UserDetailsService {

	fun save(lawFirmUser: LawFirmUser) {
		val encodePassword = passwordEncoder.encode(lawFirmUser.password)
		lawFirmUserRepository.save(
			lawFirmUser.copy(
				password = encodePassword,
				role = LawFirmUser.Role.ADMIN
			)
		)
	}

	override fun loadUserByUsername(username: String): UserDetails {
		val lawFirmUser = lawFirmUserRepository.findByEmail(username) ?: throw UsernameNotFoundException("Not exist User.")
		if(lawFirmUser.role == LawFirmUser.Role.USER) {
			val lawFirm = lawFirmRepository.findById(lawFirmUser.lawFirmId).get()
			lawFirmUser.lawFirm = lawFirm
		}
		return SecurityUser(lawFirmUser)
	}
}