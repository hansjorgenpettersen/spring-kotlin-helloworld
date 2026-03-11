package com.example.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class HelloWorldApplication

fun main(args: Array<String>) {
    runApplication<HelloWorldApplication>(*args)
}

@RestController
class HelloWorldController {

    @GetMapping("/")
    fun hello(): String = "Hello World"
}
