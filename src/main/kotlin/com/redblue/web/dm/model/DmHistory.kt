package com.redblue.web.dm.model

import com.redblue.web.company.model.Executive
import net.bytebuddy.utility.RandomString
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "DM_HISTORY")
data class DmHistory(

	@Id
	val id: String = "DH" + RandomString.make(30),

	val lawFirmId: String,

	val companyName: String,

	val address: String,

	@Enumerated(EnumType.STRING)
	val state: State = State.CREATED,

	val content: String,

	@Temporal(TemporalType.DATE)
	val createdAt: Date? = Date(),

	@Temporal(TemporalType.DATE)
	val updatedAt: Date? = Date(),

	@Transient
	var executives: MutableList<Executive>? = mutableListOf()

)