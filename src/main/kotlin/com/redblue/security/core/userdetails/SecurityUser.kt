package com.redblue.security.core.userdetails

import com.redblue.web.lawfirm.model.LawFirmUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class SecurityUser(

	val lawFirmUser: LawFirmUser

): UserDetails {
	override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
		return mutableListOf<GrantedAuthority>(SimpleGrantedAuthority(lawFirmUser.role.name))
	}

	override fun getPassword(): String {
		return lawFirmUser.password
	}

	override fun getUsername(): String {
		return lawFirmUser.email
	}

	override fun isAccountNonExpired(): Boolean {
		return true
	}

	override fun isAccountNonLocked(): Boolean {
		return true
	}

	override fun isCredentialsNonExpired(): Boolean {
		return true
	}

	override fun isEnabled(): Boolean {
		return true
	}

}