package com.redblue.web.dm.repository.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.redblue.web.dm.model.DmHistory
import com.redblue.web.dm.model.QDmHistory
import com.redblue.web.dm.repository.DmHistoryQueryDslRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class DmHistoryQueryDslRepositoryImpl(
	private val jpaQueryFactory: JPAQueryFactory
) : DmHistoryQueryDslRepository, QuerydslRepositorySupport(DmHistory::class.java) {

	override fun findHistory(lawFirmId: String, q: String?, startDate: Date?, endDate: Date?, pageable: Pageable): Page<DmHistory> {
		val qd = QDmHistory.dmHistory

		val predicate = BooleanBuilder()

		val query = jpaQueryFactory.selectFrom(qd).orderBy(qd.createdAt.desc())

		predicate.and(
			qd.lawFirmId.eq(lawFirmId)
		)
		q?.let {
			predicate.and(
				qd.companyName.likeIgnoreCase("%$it%")
			)
		}
		startDate?.let {
			predicate.and(qd.createdAt.between(it, endDate))
		}

		query.where(predicate)

		/**
		 * val consults = querydsl?.applyPagination(pageable, query)?.fetch()
		return PageImpl(consults ?: emptyList(), pageable, query.fetchCount())
		 */

		val histories = querydsl?.applyPagination(pageable, query)?.fetch()

		return  PageImpl(histories ?: emptyList(), pageable, query.fetchCount())
	}
}