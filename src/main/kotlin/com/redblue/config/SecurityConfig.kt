package com.redblue.config

import com.redblue.web.lawfirm.service.LawFirmUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(
	private val lawFirmUserService: LawFirmUserService,
	private val passwordEncoder: PasswordEncoder
) : WebSecurityConfigurerAdapter() {

	override fun configure(web: WebSecurity) {
		web.ignoring().antMatchers("/static/**")
	}

	override fun configure(auth: AuthenticationManagerBuilder) {
		auth.userDetailsService(lawFirmUserService)
			.passwordEncoder(passwordEncoder)
	}

	override fun configure(http: HttpSecurity) {
		http.anonymous()
			.and()
			.formLogin()
			.and()
			.authorizeRequests()
			.anyRequest().authenticated()
	}

}