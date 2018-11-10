package szarch.bme.hu.ibdb.network.models.book

import szarch.bme.hu.ibdb.network.models.category.CategoryResponse
import szarch.bme.hu.ibdb.network.models.review.ReviewResponse
import java.util.*

data class BookResponse(
    val id: String,
    val title: String,
    val author: String,
    val published: Date,
    val publisher: String,
    val reviews: List<ReviewResponse>,
    val categories: List<CategoryResponse>,
    val imageUrl: String,
    val summary: String,
    val pageNumber: Int,
    val sold: Int,
    val views: Int,
    val averageRating: Double
)