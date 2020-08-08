package com.redblue

import org.h2.tools.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@SpringBootApplication
class RedBlueApplication

fun main(args: Array<String>) {
    runApplication<RedBlueApplication>(*args)
}

@Configuration
@Profile("local")
class LocalConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    fun h2Server(): Server {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092")
    }

}
