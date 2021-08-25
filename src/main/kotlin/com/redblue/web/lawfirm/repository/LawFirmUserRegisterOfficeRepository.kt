package com.redblue.web.lawfirm.repository

import com.redblue.web.lawfirm.model.LawFirmUserRegisterOffice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface LawFirmUserRegisterOfficeRepository: JpaRepository<LawFirmUserRegisterOffice, LawFirmUserRegisterOffice.Id> {

	@Query("select lugo from LawFirmUserRegisterOffice lugo where lugo.id.userId = :userId")
	fun findByUserId(userId: String): List<LawFirmUserRegisterOffice>

	@Modifying
	@Query("delete from LawFirmUserRegisterOffice lugo where lugo.id.userId = :userId")
	fun deleteByUserId(userId: String)

}