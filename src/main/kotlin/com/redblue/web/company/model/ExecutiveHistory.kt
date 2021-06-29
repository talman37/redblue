package com.redblue.web.company.model

import com.redblue.persistence.JsonToExeHistoryConverter
import net.bytebuddy.utility.RandomString
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "EXECUTIVE_HISTORY")
data class ExecutiveHistory(

	@Id
	val id: String? = "EH" + RandomString.make(29),

	@Enumerated(EnumType.STRING)
	val type: IssuedType? = null,

	val companyId: String? = null,

	@Convert(converter = JsonToExeHistoryConverter::class)
	val data: List<Executive> = mutableListOf(),

	@Temporal(TemporalType.DATE)
	val issuedAt: Date? = Date()

)
