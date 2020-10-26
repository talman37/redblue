package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Stock
import java.util.*

data class CompanySubUpdateDto(

	val companyAddress: String? = null,

	val companyPostalCode: String? = null,

	val companyAddressUpdatedAt: Date? = null,

	val deliveryPlace: String? = null,

	val deliveryPlacePostalCode: String? = null,

	val businessNumber: String? = null,

	val businessType: String? = null,

	val businessCondition: String? = null,

	val settlementMonth: Int? = null,

	val stock: Stock? = null


)