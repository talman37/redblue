package com.redblue.web.dm.model

import javax.persistence.*

@Entity
@Table(name = "DM")
data class Dm(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Int,

	val name: String,

	val resourcePath: String

)
