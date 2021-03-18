package com.redblue.web.admin.user.service

import com.redblue.web.lawfirm.model.LawFirmUser

interface UserService {

	fun find(): List<LawFirmUser>

}