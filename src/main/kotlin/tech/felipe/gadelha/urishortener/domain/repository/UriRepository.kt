package tech.felipe.gadelha.urishortener.domain.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import tech.felipe.gadelha.urishortener.domain.model.Uri

@EnableMongoRepositories
interface UriRepository: MongoRepository<Uri, String> {
}