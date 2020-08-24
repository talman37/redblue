package com.redblue.web.lawfirm.repository

import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LawFirmUserRepository: JpaRepository<LawFirmUser, String> {

	fun findByEmail(email: String): LawFirmUser?

}