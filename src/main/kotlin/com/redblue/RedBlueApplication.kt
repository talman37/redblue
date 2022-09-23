package com.redblue

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedBlueApplication

fun main(args: Array<String>) {
    runApplication<RedBlueApplication>(*args)
}
