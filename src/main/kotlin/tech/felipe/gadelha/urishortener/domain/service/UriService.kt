package tech.felipe.gadelha.urishortener.domain.service

import jakarta.servlet.http.HttpServletRequest
import org.apache.commons.lang3.RandomStringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import tech.felipe.gadelha.urishortener.api.dto.request.ShortenUriRq
import tech.felipe.gadelha.urishortener.api.dto.response.ShortenUriRs
import tech.felipe.gadelha.urishortener.api.v1.controller.UriController
import tech.felipe.gadelha.urishortener.domain.model.Uri
import tech.felipe.gadelha.urishortener.domain.repository.UriRepository
import java.net.URI
import java.time.Instant

@Service
class UriService(
    private val uriRepository: UriRepository
) {
    companion object{
        private val logger: Logger = LoggerFactory.getLogger(UriService::class.java)
    }

    fun findById(id: String):Uri {
        logger.info("method::redirect id={}", id)
        return uriRepository.findById(id)
            .orElseThrow{ throw RuntimeException("Dados inválidos!") }
    }

    fun shortenUrl(uri: String): String {
        logger.info("method::shortenUrl request={}", uri)
        var id: String
        do { id = RandomStringUtils.randomAlphabetic(5, 10) }
        while (uriRepository.existsById(id))

        uriRepository.save(Uri(id, uri, Instant.now().plusSeconds(60)))
        return id
    }
}