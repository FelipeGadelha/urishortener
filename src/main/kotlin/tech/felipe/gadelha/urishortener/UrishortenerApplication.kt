package tech.felipe.gadelha.urishortener

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UrishortenerApplication

fun main(args: Array<String>) {
	runApplication<UrishortenerApplication>(*args)
}
