package com.redblue.web.lawfirm.repository.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.redblue.web.lawfirm.model.QRegisterOffice
import com.redblue.web.lawfirm.model.RegisterOffice
import com.redblue.web.lawfirm.repository.RegisterOfficeQueryDslRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils

@Repository
class RegisterOfficeQueryDslRepositoryImpl(
	private val jpaQueryFactory: JPAQueryFactory
) : RegisterOfficeQueryDslRepository, QuerydslRepositorySupport(RegisterOffice::class.java) {

	override fun find(searchValue: String?): List<RegisterOffice> {
		val qr = QRegisterOffice.registerOffice
		val predicate = BooleanBuilder()

		val query = jpaQueryFactory.selectFrom(qr)

		if (!StringUtils.isEmpty(searchValue)) {
			predicate.or(qr.name.like("%$searchValue%"))
			predicate.or(qr.code.like("%$searchValue%"))
		}

		query.where(predicate)

		return query.fetch()
	}
}