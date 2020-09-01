package com.redblue.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AppConfig: WebMvcConfigurer {


	override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
		registry.addResourceHandler("/static/**")
			.addResourceLocations("classpath:/static/")
			.setCachePeriod(20)
	}

	@Bean
	fun passwordEncoder(): BCryptPasswordEncoder {
		return BCryptPasswordEncoder()
	}
}