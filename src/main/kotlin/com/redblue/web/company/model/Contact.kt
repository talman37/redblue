package com.redblue.web.company.model

import net.bytebuddy.utility.RandomString
import javax.persistence.*

@Entity
@Table(name = "CONTACTS")
data class Contact(

	@Id
	val id: String = "CT" + RandomString.make(30),

	val companyId: String? = null,

	@Enumerated(EnumType.STRING)
	val type: Type? = null,

	val value: String? = null,

	val memo: String? = null

) {

	enum class Type {
		MOBILE,
		PHONE,
		DIRECT_PHONE,
		FAX,
		EMAIL,
		TAX_EMAIL,
		FACTORY
	}

}