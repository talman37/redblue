package com.redblue.web.lawfirm.service

import com.redblue.security.core.userdetails.SecurityUser
import com.redblue.web.lawfirm.model.LawFirmUser
import com.redblue.web.lawfirm.model.LawFirmUserRegisterOffice
import com.redblue.web.lawfirm.model.dto.LawFirmUserUpdateDto
import com.redblue.web.lawfirm.repository.LawFirmRepository
import com.redblue.web.lawfirm.repository.LawFirmUserRegisterOfficeRepository
import com.redblue.web.lawfirm.repository.LawFirmUserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import javax.transaction.Transactional

@Service
class LawFirmUserService(
	private val lawFirmUserRepository: LawFirmUserRepository,
	private val lawFirmRepository: LawFirmRepository,
	private val lawFirmUserRegisterOfficeRepository: LawFirmUserRegisterOfficeRepository,
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

	fun details(id: String): LawFirmUser{
		return lawFirmUserRepository.findById(id)
			.orElseThrow {throw UsernameNotFoundException("Not exist User.")}
	}

	@Transactional
	fun update(lawFirmUserUpdateDto: LawFirmUserUpdateDto) {
		val user = lawFirmUserRepository.findById(lawFirmUserUpdateDto.id)
			.orElseThrow {throw UsernameNotFoundException("Not exist User.")}
		user.name = lawFirmUserUpdateDto.name
		if(StringUtils.hasText(lawFirmUserUpdateDto.password)) {
			user.password = passwordEncoder.encode(lawFirmUserUpdateDto.password)
		}
		lawFirmUserRepository.save(user)

		val registerOfficeList = mutableListOf<LawFirmUserRegisterOffice>()

		lawFirmUserUpdateDto.lawFirmUserRegisterOffices.forEach {
			registerOfficeList.add(
				LawFirmUserRegisterOffice(
					id = LawFirmUserRegisterOffice.Id(lawFirmUserUpdateDto.id, it.id),
					name = it.name
				)
			)
		}

		if(registerOfficeList.isNotEmpty()) {
			lawFirmUserRegisterOfficeRepository.saveAll(registerOfficeList)
		}

	}

	@Transactional
	fun deleteOffice(id: String, officeId: String) {
		lawFirmUserRegisterOfficeRepository.deleteByIdUserIdAndIdRegisterOfficeId(id, officeId)
	}

	override fun loadUserByUsername(username: String): UserDetails {
		val lawFirmUser = lawFirmUserRepository.findByEmail(username) ?: throw UsernameNotFoundException("Not exist User.")
		if(lawFirmUser.role == LawFirmUser.Role.ADMIN) {
			val lawFirm = lawFirmRepository.findById(lawFirmUser.lawFirmId).get()
			lawFirmUser.lawFirm = lawFirm
		}
		return SecurityUser(lawFirmUser)
	}

}