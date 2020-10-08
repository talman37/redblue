package com.redblue.web.lawfirm.model

import javax.persistence.Entity
import javax.persistence.Table
import java.io.Serializable
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId

@Entity
@Table(name = "LAW_FIRM_USER_REGISTER_OFFICES")
data class LawFirmUserRegisterOffice(

	@EmbeddedId
	val id: Id,

	val name: String

) {

	@Embeddable
	data class Id(

		val userId: String,

		val registerOfficeId: String

	): Serializable

}