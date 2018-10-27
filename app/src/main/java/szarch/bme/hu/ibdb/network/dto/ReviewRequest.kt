package szarch.bme.hu.ibdb.network.dto

data class ReviewRequest(
    val points: Int,
    val comment: String
)