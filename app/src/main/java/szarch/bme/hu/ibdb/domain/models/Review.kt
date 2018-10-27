package szarch.bme.hu.ibdb.domain.models

import java.time.OffsetDateTime

data class Review(
    val id: String? = null,
    val user: User? = null,
    val book: Book? = null,
    val points: Int = 0,
    val date: OffsetDateTime? = null,
    val comment: String? = null
)