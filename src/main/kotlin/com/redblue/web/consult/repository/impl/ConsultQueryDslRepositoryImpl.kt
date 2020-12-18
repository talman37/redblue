package com.redblue.web.consult.repository.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.ExpressionUtils
import com.querydsl.core.types.Projections
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import com.redblue.web.company.model.QCompany
import com.redblue.web.company.model.QExecutive
import com.redblue.web.consult.model.Consult
import com.redblue.web.consult.model.QConsult
import com.redblue.web.consult.repository.ConsultQueryDslRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ConsultQueryDslRepositoryImpl(
	private val jpaQueryFactory: JPAQueryFactory
) : ConsultQueryDslRepository, QuerydslRepositorySupport(Consult::class.java) {

	override fun findByLawFirmId(lawFirmId: String, searchValue:String?, startDate: Date?, endDate: Date?, progress: List<Consult.Progress>?, pageable: Pageable): Page<Consult> {
		val qc = QConsult.consult
		val qp = QCompany.company
		val qe = QExecutive.executive
		val predicate = BooleanBuilder()
		val query = jpaQueryFactory
			.select(
				Projections.fields(
					qc,
					qc.id,
					qc.lawFirmId,
					qc.companyId,
					qp.companyName,
					ExpressionUtils.`as`(JPAExpressions.select(qe.expiredAt.min())
						.from(qe).where(qe.companyId.eq(qc.companyId)).limit(1), "expiredAt"),
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

		searchValue?.let {
			predicate.and(qp.companyName.likeIgnoreCase("%$it%"))
		}

		startDate?.let {
			predicate.and(qe.expiredAt.between(it, endDate))
		}

		progress?.let {
			if(progress.isNotEmpty()) {
				predicate.and(qc.progress.`in`(it))
			}
		}

		query.where(predicate)
		val consults = querydsl?.applyPagination(pageable, query)?.fetch()
		return PageImpl(consults ?: emptyList(), pageable, query.fetchCount())
	}
}