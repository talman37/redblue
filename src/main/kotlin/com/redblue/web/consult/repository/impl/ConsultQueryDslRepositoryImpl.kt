package com.redblue.web.consult.repository.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.redblue.web.company.model.QCompany
import com.redblue.web.consult.model.Consult
import com.redblue.web.consult.model.QConsult
import com.redblue.web.consult.repository.ConsultQueryDslRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class ConsultQueryDslRepositoryImpl(
	private val jpaQueryFactory: JPAQueryFactory
) : ConsultQueryDslRepository, QuerydslRepositorySupport(Consult::class.java) {

	override fun findByLawFirmId(lawFirmId: String, pageable: Pageable): Page<Consult> {
		val qc = QConsult.consult
		val qp = QCompany.company
		val predicate = BooleanBuilder()
		val query = jpaQueryFactory
			.select(
				Projections.fields(
					qc,
					qc.id,
					qc.lawFirmId,
					qc.companyId,
					qp.companyName,
					qc.consultant,
					qc.content,
					qc.memo,
					qc.progress,
					qc.updatedAt,
					qc.createdAt
				)
			)
			.from(qc)
			.innerJoin(qp).on(qc.companyId.eq(qp.id))
			.orderBy(qc.createdAt.desc())

		predicate.and(qc.lawFirmId.eq(lawFirmId))
		query.where(predicate)
		val consults = querydsl?.applyPagination(pageable, query)?.fetch()
		return PageImpl(consults ?: emptyList(), pageable, query.fetchCount())
	}
}