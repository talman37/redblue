package com.redblue.web.user.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "USERS")
data class User(

	@Id
	val id: String,

	val lawFirmId: String,

	val username: String,

	val password: String,

	val name: String,

	val email: String,

	val createdAt: Date


)