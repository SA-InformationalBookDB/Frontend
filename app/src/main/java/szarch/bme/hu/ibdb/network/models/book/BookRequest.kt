package szarch.bme.hu.ibdb.network.models.book

import java.util.*

data class BookRequest(
    val title: String,
    val author: String,
    val published: Date?,
    val publisher: String?,
    val imageUrl: String?,
    val summary: String,
    val pageNumber: Int,
    val sold: Int?,
    val views: Int?
)