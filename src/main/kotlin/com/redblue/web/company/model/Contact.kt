package com.redblue.web.company.model

import net.bytebuddy.utility.RandomString
import javax.persistence.*

@Entity
@Table(name = "CONTACTS")
data class Contact(

	@Id
	val id: String = "CT" + RandomString.make(30),

	var companyId: String? = null,

	@Enumerated(EnumType.STRING)
	val type: Type? = null,

	val value: String? = null,

	val memo: String? = null

) {

	enum class Type(val text: String) {
		MOBILE("휴대폰"),
		PHONE("유선전화"),
		DIRECT_PHONE("직통번호"),
		FAX("회사팩스"),
		EMAIL("이메일"),
		TAX_EMAIL("세금계산서용 이메일"),
		FACTORY("공장")
	}

}