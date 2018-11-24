package szarch.bme.hu.ibdb.ui.reviews

import szarch.bme.hu.ibdb.network.models.review.Review

interface ReviewsScreen {

    fun showReviews(reviews: List<Review>)
    fun showActionResult(message: String)

}
