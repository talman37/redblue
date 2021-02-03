package com.redblue.web.company.repository.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.ExpressionUtils
import com.querydsl.core.types.Projections
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import com.redblue.web.company.model.Company
import com.redblue.web.company.model.QCompany
import com.redblue.web.company.model.QExecutive
import com.redblue.web.company.repository.CompanyQueryDslRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.math.min

@Repository
class CompanyQueryDslRepositoryImpl(
	private val jpaQueryFactory: JPAQueryFactory
) : CompanyQueryDslRepository, QuerydslRepositorySupport(Company::class.java) {

	override fun findByLawFirmId(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, pageable: Pageable): Page<Company> {
		val qc = QCompany.company
		val qe = QExecutive.executive
		val predicate = BooleanBuilder()

		val query = jpaQueryFactory
			.select(
				Projections.fields(
					qc,
					qc.id,
					qc.lawFirmId,
					qc.registerOffice,
					qc.registerNumber,
					qc.companyName,
					qc.companyAddress,
					qc.companyNumber1,
					qc.companyNumber2,
					qc.deliveryPlacePostalCode,
					qc.deliveryPlace,
					ExpressionUtils.`as`(JPAExpressions.select(qe.expiredAt.min())
						.from(qe).where(qe.companyId.eq(qc.id)).limit(1), "expiredAt")
				)
			)
			.from(qc)
			.leftJoin(qe).on(qc.id.eq(qe.companyId)).fetchJoin()
		predicate.and(
			qc.lawFirmId.eq(lawFirmId)
		)

		q?.let {
			predicate.and(
				qc.companyName.likeIgnoreCase("%$it%")
					.or(qc.companyNumber1.like(q))
					.or(qc.companyNumber2.like(q))
			)
		}

		startDate?.let {
			predicate.and(qe.expiredAt.between(it, endDate))
		}

		query.where(predicate)
		val companies = querydsl?.applyPagination(pageable, query)?.distinct()?.fetch()
		return PageImpl(companies ?: emptyList(), pageable, query.distinct().fetchCount())
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

	override fun findByLawFirmIdAndCompanyName(lawFirmId: String, companyName: String): List<Company> {
		val qc = QCompany.company
		val qe = QExecutive.executive
		val predicate = BooleanBuilder()

		val query = jpaQueryFactory
			.select(
				Projections.fields(
					qc,
					qc.id,
					qc.lawFirmId,
					qc.registerOffice,
					qc.registerNumber,
					qc.companyName,
					qc.companyAddress,
					qc.deliveryPlacePostalCode,
					qc.deliveryPlace,
					ExpressionUtils.`as`(JPAExpressions.select(qe.expiredAt.min())
						.from(qe).where(qe.companyId.eq(qc.id)).limit(1), "expiredAt")
				)
			)
			.from(qc)
			.leftJoin(qe).on(qc.id.eq(qe.companyId)).fetchJoin()
		predicate.and(
			qc.lawFirmId.eq(lawFirmId)
		)
		predicate.and(qc.companyName.likeIgnoreCase("%$companyName%"))

		query.where(predicate)
		return query.distinct().fetch()
	}
}