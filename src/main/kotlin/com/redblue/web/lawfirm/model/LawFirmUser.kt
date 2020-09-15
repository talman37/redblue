package com.redblue.web.lawfirm.model

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

	@Temporal(TemporalType.DATE)
	val createdAt: Date

) {
	enum class Role {
		ADMIN
	}
}