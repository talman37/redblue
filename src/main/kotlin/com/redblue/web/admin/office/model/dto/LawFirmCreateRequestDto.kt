package com.redblue.web.admin.office.model.dto

import com.redblue.web.lawfirm.model.LawFirm
import net.bytebuddy.utility.RandomString
import java.util.*

data class LawFirmCreateRequestDto(

	val name: String,

	val address: String,

	val postalCode: String,

	val tel: String,

	val fax: String,

	val description: String,

	val etc: String,

	val representative: String

) {
	fun to(): LawFirm {
		return LawFirm(
			id = "LAW" + RandomString.make(39),
			name = this.name,
			address = this.address,
			postalCode = this.postalCode,
			tel = this.tel,
			fax = this.fax,
			description = this.description,
			etc = this.etc,
			representative = this.representative,
			createdAt = Date()
		)
	}
}
