package com.redblue.web.dm.repository.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.redblue.web.dm.model.Dm
import com.redblue.web.dm.model.DmHistory
import com.redblue.web.dm.model.QDm
import com.redblue.web.dm.model.QDmHistory
import com.redblue.web.dm.repository.DmHistoryQueryDslRepository
import com.redblue.web.dm.repository.DmQueryDslRepository
import com.redblue.web.lawfirm.model.QLawFirmDm
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class DmQueryDslRepositoryImpl(
	private val jpaQueryFactory: JPAQueryFactory
) : DmQueryDslRepository, QuerydslRepositorySupport(Dm::class.java) {

	override fun findByLawFirmId(lawFirmId: String): List<Dm> {
		val qd = QDm.dm
		val qlfd = QLawFirmDm.lawFirmDm

		val predicate = BooleanBuilder()

		val query = jpaQueryFactory.selectFrom(qd)
			.leftJoin(qlfd).on(qd.id.eq(qlfd.dmId))

		predicate.and(
			qlfd.lawFirmId.eq(lawFirmId)
		)

		query.where(predicate)

		return query.fetch()
	}

}