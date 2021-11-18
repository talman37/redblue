package com.redblue.web.company.repository.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.ExpressionUtils
import com.querydsl.core.types.Projections
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import com.redblue.web.company.model.*
import com.redblue.web.company.repository.CompanyQueryDslRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils
import java.util.*
import kotlin.math.min

@Repository
class CompanyQueryDslRepositoryImpl(
	private val jpaQueryFactory: JPAQueryFactory
) : CompanyQueryDslRepository, QuerydslRepositorySupport(Company::class.java) {

	override fun findByLawFirmId(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, companyState: MutableList<String>, searchType: String?, positionTarget: String?, modifiedStartDate: Date?, modifiedEndDate: Date?, searchRange: String?, registerOffice: String?): List<Company> {
		val qc = QCompany.company
		val qe = QExecutive.executive
		val qct = QContact.contact
		val predicate = BooleanBuilder()

		val query = jpaQueryFactory
			.selectFrom(qc
			)
			.from(qc)
			.leftJoin(qe).on(qc.id.eq(qe.companyId)).fetchJoin()
			.leftJoin(qct).on(qc.id.eq(qct.companyId)).fetchJoin()
		predicate.and(
			qc.lawFirmId.eq(lawFirmId)
		)

		if(companyState.isNotEmpty()) {
			predicate.and(qc.companyManageState.`in`(companyState))
		}
		if(searchRange == "기타임기법인") {
			predicate.and(
				qe.term.ne(3)
			)
		}

		q?.let {
			when (searchRange) {
				"법인등록번호" -> {
					predicate.and(
						qc.companyNumber1.like("%$it%")
							.or(qc.companyNumber2.like("%$it%"))
					)
				}
				"등기번호" -> {
					predicate.and(
						qc.registerNumber.eq(it.replace("[^0-9]".toRegex(), "").toInt())
					)
				}
				"법인명" -> {
					if(it.length > 2) {
						predicate.and(
							qc.companyName.likeIgnoreCase("%$it%")
						)
					} else {
						predicate.and(
							qc.companyName.eq(it)
						)
					}
				}
				"법인관리번호" -> {
					predicate.and(
						qc.companyManageNumber.like("%$it%")
					)
				}
				"주소" -> {
					predicate.and(
						qc.companyAddress.like("%$it%")
					)
				}
				"임원이름" -> {
					predicate.and(
						qe.name.like("%$it%")
					)
				}
				else -> {
					predicate.and(
						qc.companyName.likeIgnoreCase("%$it%")
							.or(qc.companyNumber1.like("%$it%"))
							.or(qc.companyNumber2.like("%$it%"))
							.or(qc.companyManageNumber.like("%$it%"))
							.or(qc.companyAddress.like("%$it%"))
							.or(qc.registerNumber.like("%$it%"))
							.or(qe.name.like("%$it%"))
					)
				}
			}
		}

		searchType?.let {
			when (it) {
				"ALL" -> {
				}
				else -> {
					predicate.and(qc.companyState.eq(searchType))
				}
			}
		}

		startDate?.let {
			predicate.and(qe.expiredAt.between(it, endDate))
			if(StringUtils.hasText(positionTarget)) {
				predicate.and(qe.position.eq("감사"))
			} else {
				predicate.and(qe.position.ne("감사"))
			}
		}

		modifiedStartDate?.let {
			predicate.and(qc.updatedAt.between(it, modifiedEndDate))
		}

		registerOffice?.let {
			if(StringUtils.hasText(it)) {
				predicate.and(qc.registerOffice.eq(it))
			}
		}

		query.where(predicate)
			.orderBy(qc.registerOffice.asc())
			.orderBy(qc.registerNumber.asc())
		var companies = query.distinct().fetch()

		startDate?.let {
			if(StringUtils.hasText(positionTarget)) {
				var companyIds = companies.map { company -> company.id }
				var inQuery = jpaQueryFactory.selectFrom(qe)
				val inPredicate = BooleanBuilder()
				inPredicate.and(qe.companyId.`in`(companyIds))
				inPredicate.and(qe.position.ne("감사"))
				inPredicate.and(qe.expiredAt.between(it, endDate))
				inQuery.where(inPredicate)
				val exceptIds = inQuery.fetch().map { executive -> executive.companyId }.distinct()
				companies = companies.filter { c -> !exceptIds.contains(c.id) }
			}
		}
		return companies
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