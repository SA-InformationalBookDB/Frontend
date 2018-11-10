package szarch.bme.hu.ibdb.network.models.base

data class PageResponseBase<T>(
    val content: T,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: PageableResponse,
    val size: Int,
    val sort: SortResponse,
    val totalElements: Int,
    val totalPages: Int

)