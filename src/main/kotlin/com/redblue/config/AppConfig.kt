package com.redblue.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AppConfig: WebMvcConfigurer {


	override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
		registry.addResourceHandler("/static/**")
			.addResourceLocations("classpath:/static/")
			.setCachePeriod(0)
	}

	@Bean
	fun passwordEncoder(): BCryptPasswordEncoder {
		return BCryptPasswordEncoder()
	}

	override fun addViewControllers(registry: ViewControllerRegistry) {
		registry.addViewController("/login").setViewName("/login/loginForm");
		registry.addViewController("/logout").setViewName("/login/loginForm");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE)
	}

	override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
		converters.add(MappingJackson2HttpMessageConverter())
		super.configureMessageConverters(converters)
	}
}