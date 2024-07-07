package tech.felipe.gadelha.urishortener.domain.exception

data class EntityNotFoundException(override val message: String?): RuntimeException(message)
