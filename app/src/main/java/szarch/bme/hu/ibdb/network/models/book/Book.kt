package szarch.bme.hu.ibdb.network.models.book

import szarch.bme.hu.ibdb.network.models.category.Category
import szarch.bme.hu.ibdb.network.models.review.Review
import java.util.*

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val published: Date?,
    val publisher: String?,
    val favourite: Boolean?,
    val reviews: List<Review>?,
    val categories: List<Category>,
    val imageUrl: String?,
    val summary: String,
    val pageNumber: Int,
    val sold: Int?,
    val views: Int?,
    val averageRating: Double?
)