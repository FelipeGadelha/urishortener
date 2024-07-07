package tech.felipe.gadelha.urishortener.api.v1.controller

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.felipe.gadelha.urishortener.api.dto.request.ShortenUriRq
import tech.felipe.gadelha.urishortener.api.dto.response.ShortenUriRs
import tech.felipe.gadelha.urishortener.domain.service.UriService
import java.net.URI

@RestController
@RequestMapping("/v1/uri")
class UriController(
    private val uriService: UriService
) {

    companion object{
        private val logger: Logger = LoggerFactory.getLogger(UriController::class.java)
    }

    @GetMapping("{id}")
    fun redirect(@PathVariable id: String): ResponseEntity<Any> {
        logger.info("method::redirect id={}", id)
        val url = uriService.findById(id)
        val headers = HttpHeaders()
        headers.location = URI.create(url.fullUri)
        return ResponseEntity
            .status(HttpStatus.FOUND)
            .headers(headers)
            .build()
    }

    @PostMapping("/shorten-url")
    fun shortenUrl(@RequestBody request: ShortenUriRq, servletRequest: HttpServletRequest): ResponseEntity<Any> {
        logger.info("method::shortenUrl request={}", request.uri)
        val id = uriService.shortenUrl(request.uri)

        val redirectUrl = servletRequest.requestURL.toString()
            .replace("shorten-url", id)
        logger.info("method::shortenUrl redirectUrl={}", redirectUrl)
        return ResponseEntity.ok(ShortenUriRs(redirectUrl))
    }
}