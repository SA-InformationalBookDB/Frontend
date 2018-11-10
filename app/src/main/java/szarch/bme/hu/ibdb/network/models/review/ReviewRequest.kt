package szarch.bme.hu.ibdb.network.models.review

data class ReviewRequest(
    val comment: String,
    val points: Int
)