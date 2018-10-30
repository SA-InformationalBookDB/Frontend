package szarch.bme.hu.ibdb.domain.models

import java.time.OffsetDateTime

data class User(
    val id: String,
    val email: String,
    val password: String,
    val dateOfBirth: OffsetDateTime?,
    val favourites: List<Favourite> = emptyList(),
    val categories: List<Category> = emptyList(),
    val reviews: List<Review> = emptyList(),
    val role: Role = Role.USER,
    val isEnabled: Boolean = true
)