package tech.felipe.gadelha.urishortener.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import java.time.Instant

@Document(collection = "uris")
class Uri(
    @Id
    var id: String? = null,

    val fullUri: String = "",

    @Indexed(expireAfterSeconds = 0)
    val expiresAt: Instant = Instant.now()
)