package com.redblue.web.consult.model

import net.bytebuddy.utility.RandomString
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "CONSULTS")
data class Consult(

	@Id
	val id: String = "CS" + RandomString.make(30),

	val lawFirmId: String,

	val companyId: String,

	@Transient
	val companyName: String? = null,

	val consultant: String,

	val content: String,

	val memo: String,

	@Enumerated(EnumType.STRING)
	val progress: Progress,

	@Temporal(TemporalType.DATE)
	val createdAt: Date = Date(),

	@Temporal(TemporalType.DATE)
	val updatedAt: Date? = null

) {
	enum class Progress(val keyword: String) {
		ONGOING("진행중"),
		COMPLETE("진행완료"),
		CANCEL("취소"),
		HOLD("보류")
	}
}