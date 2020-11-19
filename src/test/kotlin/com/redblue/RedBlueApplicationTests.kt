package com.redblue

import net.bytebuddy.utility.RandomString
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootTest
class RedBlueApplicationTests {

    @Test
    fun contextLoads() {
        val encoder = BCryptPasswordEncoder()
        println(encoder.encode("redblue2020!!"))
    }

}
