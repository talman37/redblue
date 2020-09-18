package com.redblue.web.company.model

import net.bytebuddy.utility.RandomString
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "STOCKHOLDERS")
data class Stockholder(

	@Id
	val id: String = "SH" + RandomString.make(30),

	var companyId: String? = null,

	val name: String? = null,

	val registrationNumber1: String? = null,

	val registrationNumber2: String? = null,

	val address: String? = null,

	val stockCount: Int? = 0

)