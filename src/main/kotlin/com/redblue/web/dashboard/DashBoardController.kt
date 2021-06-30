package com.redblue.web.dashboard

import com.redblue.security.core.annotation.CurrentUser
import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class DashBoardController {

	@GetMapping()
	fun dashBoard(@CurrentUser user: LawFirmUser) :String {
		if(user.role == LawFirmUser.Role.SYSTEM) {
			return "redirect:/admin/users"
		}
		return "redirect:/company"
	}

}