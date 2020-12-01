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

	val consultant: String,

	@Transient
	val companyName: String? = null,

	@Transient
	val expiredAt: Date? = null,

	val content: String,

	val memo: String,

	@Enumerated(EnumType.STRING)
	val progress: Progress,

	@Temporal(TemporalType.TIMESTAMP)
	val createdAt: Date = Date(),

	@Temporal(TemporalType.TIMESTAMP)
	val updatedAt: Date? = null

) {
	enum class Progress(val keyword: String) {
		ONGOING("진행중"),
		COMPLETE("진행완료"),
		CANCEL("취소"),
		HOLD("보류")
	}
}