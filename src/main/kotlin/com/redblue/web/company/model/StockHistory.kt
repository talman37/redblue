package com.redblue.web.company.model

import net.bytebuddy.utility.RandomString
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "STOCK_HISTORY")
data class StockHistory(

	@Id
	val id: String = "SH" + RandomString.make(30),

	val companyId: String? = null,

	val stockId: String? = null,

	@Enumerated(EnumType.STRING)
	val type: IssuedType? = null,

	val amount: Long? = 0,

	val scheduleCount: Long? = 0,

	val issuedCount: Long? = 0,

	@Temporal(TemporalType.DATE)
	val issuedAt: Date? = Date()

)
