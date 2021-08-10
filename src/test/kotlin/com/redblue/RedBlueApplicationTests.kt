package com.redblue

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootTest
class RedBlueApplicationTests {

	@Test
	fun contextLoads() {
		val encoder = BCryptPasswordEncoder()
		println(encoder.encode("ekdrn1200"))
		println(encoder.encode("ekdrn1400"))
	}

}
