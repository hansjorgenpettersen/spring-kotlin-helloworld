package com.example.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
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

    private val restTemplate = RestTemplate()

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

    @GetMapping("/weather")
    fun weather(): Map<String, Any> {
        val url = "https://api.open-meteo.com/v1/forecast?latitude=60.3680801369957&longitude=7.503686220036417&current_weather=true"
        val response = restTemplate.getForObject(url, Map::class.java) ?: emptyMap<String, Any>()
        @Suppress("UNCHECKED_CAST")
        val current = response["current_weather"] as? Map<String, Any> ?: emptyMap()
        return mapOf(
            "location" to "Ål, Norway",
            "temperature" to "${current["temperature"]}°C",
            "windspeed" to "${current["windspeed"]} km/h",
            "time" to (current["time"] ?: "unknown")
        )
    }

    @GetMapping("/joke")
    fun joke(): Map<String, Any> {
        val url = "https://v2.jokeapi.dev/joke/Programming?type=twopart"
        val response = restTemplate.getForObject(url, Map::class.java) ?: emptyMap<String, Any>()
        return mapOf(
            "setup" to (response["setup"] ?: ""),
            "punchline" to (response["delivery"] ?: "")
        )
    }
}
