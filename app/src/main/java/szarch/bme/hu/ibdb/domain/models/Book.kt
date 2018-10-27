package szarch.bme.hu.ibdb.domain.models

import java.time.OffsetDateTime

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val published: OffsetDateTime?,
    val publisher: String,
    val reviews: List<Review>,
    val categories: List<Category>,
    val imageUrl: String,
    val summary: String,
    val pageNumber: Int = 0,
    val sold: Int = 0,
    val views: Int = 0
)