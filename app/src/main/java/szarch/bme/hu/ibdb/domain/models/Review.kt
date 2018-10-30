package szarch.bme.hu.ibdb.domain.models

import java.time.OffsetDateTime

data class Review(
    val id: String,
    val user: User,
    val book: Book,
    val points: Double,
    val date: OffsetDateTime?,
    val comment: String?
)