package szarch.bme.hu.ibdb.network.models.base

data class PageableResponse(
    val offset: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val paged: Boolean,
    val sort: SortResponse,
    val unpaged: Boolean
)