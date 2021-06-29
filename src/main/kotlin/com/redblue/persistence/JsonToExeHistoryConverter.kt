package com.redblue.persistence

import com.fasterxml.jackson.databind.ObjectMapper
import com.redblue.web.company.model.Executive
import javax.persistence.AttributeConverter

class JsonToExeHistoryConverter(): AttributeConverter<MutableList<Executive>, String> {

	override fun convertToDatabaseColumn(executives: MutableList<Executive>?): String? {
		if(executives == null) {
			return null
		}
		return ObjectMapper().writeValueAsString(executives)
	}

	override fun convertToEntityAttribute(dbData: String?): MutableList<Executive> {
		if (dbData.isNullOrEmpty()) {
			return mutableListOf()
		}
		val executives = mutableListOf<Executive>()
		val exes : Array<Executive> = ObjectMapper().readValue(dbData, Array<Executive>::class.java)
		executives.addAll(exes)
		return executives
	}
}