package szarch.bme.hu.ibdb.network.models.book

data class BookRequest(
    val title: String,
    val author: String,
    val published: String?,
    val publisher: String?,
    val imageUrl: String?,
    val summary: String,
    val pageNumber: Int,
    val sold: Int?
)