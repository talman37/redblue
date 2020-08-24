package com.redblue.web.lawfirm.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "LAW_FIRM_USERS")
data class LawFirmUser(

	@Id
	val id: String,

	val lawFirmId: String,

	val email: String,

	val password: String,

	val name: String,

	@Enumerated(EnumType.STRING)
	var role: Role,

	val createdAt: Date

) {
	fun getAuthorities(): User {
		return User(
			this.email, this.password,
			mutableListOf<GrantedAuthority>(SimpleGrantedAuthority(this.role.name))
		)
	}

	enum class Role {
		ADMIN
	}
}