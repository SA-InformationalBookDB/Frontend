package szarch.bme.hu.ibdb.network.dto

import java.time.OffsetDateTime

class BookRequest(
    val title: String? = null,
    val author: String? = null,
    val published: OffsetDateTime? = null,
    val publisher: String? = null,
    val imageUrl: String? = null,
    val summary: String? = null,
    val pageNumber: Int = 0,
    val sold: Int = 0,
    val views: Int = 0
)