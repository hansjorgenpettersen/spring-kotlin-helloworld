package com.example.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

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

    @GetMapping("/time")
    fun time(): Map<String, String> {
        val zone = ZoneId.of("Europe/Oslo")
        val now = LocalDateTime.now(zone)
        return mapOf(
            "time" to now.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
            "date" to now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            "day" to now.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH),
            "timezone" to zone.id
        )
    }

    @GetMapping("/random")
    fun random(): Map<String, Any> = mapOf(
        "random" to (1..1000).random(),
        "letter" to ('a'..'z').random(),
        "name" to "Hans Jørgen"
    )
}
