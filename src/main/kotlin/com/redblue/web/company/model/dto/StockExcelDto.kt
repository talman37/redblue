package com.redblue.web.company.model.dto

import com.redblue.web.company.model.Stock
import java.util.*

data class StockExcelDto(

	val companyName: String? = null,

	val amount: Int = 0,

	val amountUpdatedAt: Date? = null,

	val scheduleCount: Int = 0,

	val scheduleCountUpdatedAt: Date? = null,

	val issuedCount: Int = 0,

	val issuedCountUpdatedAt: Date? = null,

	val normalCount: Int = 0,

	val firstCount: Int = 0,

	val noFaceValueCount: Int = 0,

	val noFaceValueUpdatedAt: Date? = null,

	val noFaceValueCapitalAmount: Int = 0,

	val noFaceValueCapitalAmountUpdatedAt: Date? = null

) {
	companion object {

		fun to(companyName: String, stock: Stock): StockExcelDto {
			return StockExcelDto(
				companyName = companyName,
				amount = stock.amount ?: 0,
				amountUpdatedAt = stock.amountUpdatedAt,
				scheduleCount = stock.scheduleCount ?: 0,
				scheduleCountUpdatedAt = stock.scheduleCountUpdatedAt,
				issuedCount = stock.issuedCount ?: 0,
				issuedCountUpdatedAt = stock.issuedCountUpdatedAt,
				normalCount = stock.normalCount ?: 0,
				firstCount = stock.firstCount ?: 0,
				noFaceValueCount = stock.noFaceValueCount ?: 0,
				noFaceValueUpdatedAt = stock.noFaceValueUpdatedAt,
				noFaceValueCapitalAmount = stock.noFaceValueCapitalAmount ?: 0,
				noFaceValueCapitalAmountUpdatedAt = stock.noFaceValueCapitalAmountUpdatedAt
			)
		}

	}
}
