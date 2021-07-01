package com.redblue.web.admin.user.model.dto

import com.redblue.web.lawfirm.model.LawFirmUser
import net.bytebuddy.utility.RandomString
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

data class UserCreateRequestDto(

	val lawFirmId: String,

	val email: String,

	val password: String,

	val name: String

) {

	fun to(): LawFirmUser {
		return LawFirmUser(
			id = "USR" + RandomString.make(39),
			lawFirmId = this.lawFirmId,
			email = this.email,
			password = BCryptPasswordEncoder().encode(this.password),
			name = this.name,
			role = LawFirmUser.Role.ADMIN,
			createdAt = Date()
		)
	}

}