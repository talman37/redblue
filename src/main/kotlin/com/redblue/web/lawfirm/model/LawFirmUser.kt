package com.redblue.web.lawfirm.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "LAW_FIRM_USERS")
data class LawFirmUser(

	@Id
	val id: String,

	var lawFirmId: String,

	val email: String,

	var password: String,

	var name: String,

	@Enumerated(EnumType.STRING)
	var role: Role,

	@OneToMany(mappedBy = "id.userId", fetch = FetchType.EAGER)
	val lawFirmUserRegisterOffice: List<LawFirmUserRegisterOffice>? = emptyList(),

	@Temporal(TemporalType.DATE)
	val createdAt: Date,

	@Transient
	var lawFirm: LawFirm? = null

) {

	enum class Role {
		ADMIN,
		SYSTEM
	}
}