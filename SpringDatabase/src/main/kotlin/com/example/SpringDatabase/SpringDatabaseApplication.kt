package com.example.SpringDatabase

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

//@SpringBootApplication
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class SpringDatabaseApplication

fun main(args: Array<String>) {
	runApplication<SpringDatabaseApplication>(*args)
}




