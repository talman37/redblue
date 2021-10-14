package com.redblue.web.lawfirm.model

import javax.persistence.*

@Entity
@Table(name = "LAW_FIRM_DMS")
data class LawFirmDm(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Int,

	val dmId: Int,

	val lawFirmId: String
)
