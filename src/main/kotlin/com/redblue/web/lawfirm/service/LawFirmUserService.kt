package com.redblue.web.lawfirm.service

import com.redblue.web.lawfirm.model.LawFirmUser
import com.redblue.web.lawfirm.repository.LawFirmUserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LawFirmUserService(
	private val lawFirmUserRepository: LawFirmUserRepository,
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
		return lawFirmUserRepository.findByEmail(username)?.getAuthorities()
			?: throw UsernameNotFoundException("Not exist User.")
	}
}