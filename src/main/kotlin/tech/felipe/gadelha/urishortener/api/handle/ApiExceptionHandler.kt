package tech.felipe.gadelha.urishortener.api.handle

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import tech.felipe.gadelha.urishortener.domain.exception.EntityNotFoundException
import java.net.URI

@ControllerAdvice
class ApiExceptionHandler(
    @Autowired private val messageSource: MessageSource
): ResponseEntityExceptionHandler() {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val status = HttpStatus.NOT_FOUND
        val detail = ProblemDetail.forStatus(status)
        detail.title = "Not found exception"
        detail.type = URI("/not-found")
        detail.status = status.value()
        detail.detail = ex.message ?: "Erro Padrão"
        detail.setProperty("developerMessage", ex::class.java.name)

        return ResponseEntity(detail, HttpHeaders(), status)
    }
}