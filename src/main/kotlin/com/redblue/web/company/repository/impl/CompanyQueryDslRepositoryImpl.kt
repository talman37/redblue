package com.redblue.web.company.repository.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.redblue.web.company.model.Company
import com.redblue.web.company.model.QCompany
import com.redblue.web.company.repository.CompanyQueryDslRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class CompanyQueryDslRepositoryImpl(
	private val jpaQueryFactory: JPAQueryFactory
): CompanyQueryDslRepository, QuerydslRepositorySupport(Company::class.java) {

	override fun findByLawFirmId(lawFirmId: String, q: String?): List<Company> {
		val qc = QCompany.company
		val predicate = BooleanBuilder()
		val query = jpaQueryFactory.selectFrom(qc)
		predicate.and(qc.lawFirmId.eq(lawFirmId))
		q?.let {
			predicate.or(qc.companyName.like(q)).or(qc.companyNumber1.like(q)).or(qc.companyNumber2.like(q))
		}
		query.where(predicate)
		return query.fetch()
	}

	override fun search(lawFirmId: String, q: String): Company {
		val qc = QCompany.company
		val predicate = BooleanBuilder()
		val query = jpaQueryFactory.selectFrom(qc)

		q.let {
			predicate.and(qc.lawFirmId.eq(lawFirmId))
			predicate.or(qc.companyName.like(q)).or(qc.companyNumber1.like(q)).or(qc.companyNumber2.like(q))
		}
		query.where(predicate)
		return query.fetchFirst()

	}
}