package szarch.bme.hu.ibdb.ui.reviews

import szarch.bme.hu.ibdb.network.models.review.ReviewResponse

interface ReviewsScreen {

    fun showReviews(reviewsResponse: List<ReviewResponse>)
    fun showSuccessfulReviewSending()
    fun showUnsuccessfulReviewSending()

}
