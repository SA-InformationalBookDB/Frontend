package szarch.bme.hu.ibdb.network.models.book

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class BookRequest(
    val title: String,
    val author: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    val published: Date?,
    val publisher: String?,
    val imageUrl: String?,
    val summary: String,
    val pageNumber: Int,
    val sold: Int?,
    val views: Int?
)