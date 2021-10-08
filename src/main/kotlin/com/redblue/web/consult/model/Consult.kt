package com.redblue.web.consult.model

import net.bytebuddy.utility.RandomString
import org.springframework.format.annotation.DateTimeFormat
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

	val companyClerk: String? = null,

	@Transient
	val companyName: String? = null,

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	val expiredAt: Date? = null,

	val content: String,

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	val scheduledAt: Date? = null,

	@Enumerated(EnumType.STRING)
	var progress: Progress,

	@Temporal(TemporalType.TIMESTAMP)
	var createdAt: Date = Date(),

	@Temporal(TemporalType.TIMESTAMP)
	var updatedAt: Date? = null

) {
	enum class Progress(val keyword: String) {
		ONGOING("진행중"),
		COMPLETE("진행완료"),
		CANCEL("취소"),
		HOLD("보류")
	}
}