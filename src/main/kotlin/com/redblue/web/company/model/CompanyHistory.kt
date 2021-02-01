package com.redblue.web.company.model

data class CompanyHistory(

	val master: List<CompanyMasterHistory> = emptyList(),

	val sub: List<CompanySubHistory> = emptyList(),

	val stock: List<StockHistory> = emptyList()

)