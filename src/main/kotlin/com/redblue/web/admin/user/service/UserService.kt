package com.redblue.web.admin.user.service

import com.redblue.web.admin.user.model.dto.UserUpdateRequestDto
import com.redblue.web.lawfirm.model.LawFirmUser

interface UserService {

	fun find(): List<LawFirmUser>

	fun save(lawFirmUser: LawFirmUser)

	fun detail(id: String): LawFirmUser

	fun update(request: UserUpdateRequestDto)

	fun delete(id: String)

}