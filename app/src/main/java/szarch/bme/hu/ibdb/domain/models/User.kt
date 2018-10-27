package szarch.bme.hu.ibdb.domain.models

import java.time.OffsetDateTime

data class User(
    val id: String? = null,
    val email: String? = null,
    val password: String? = null,
    val dateOfBirth: OffsetDateTime? = null,
    val favourites: List<Favourite>? = null,
    val categories: List<Category>? = null,
    val reviews: List<Review>? = null,
    val role: Role? = null,
    val isEnabled: Boolean = false
)