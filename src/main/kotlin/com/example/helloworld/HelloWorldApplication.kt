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

    @GetMapping("/ole")
    fun ole(): String = "Hello Ole! :)"

    @GetMapping("/daniel")
    fun daniel(): String = "Hello Daniel! :)"

    @GetMapping("/stian")
    fun stian(): String = "Hello Stian!"

    @GetMapping("/random")
    fun random(): Map<String, Any> = mapOf(
        "random" to (1..1000).random(),
        "letter" to ('a'..'z').random()
    )
}
