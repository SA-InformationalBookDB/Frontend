package szarch.bme.hu.ibdb.domain.interactors

import szarch.bme.hu.ibdb.network.models.review.ReviewRequest
import szarch.bme.hu.ibdb.network.models.review.ReviewResponse
import szarch.bme.hu.ibdb.network.repository.ReviewRepository
import javax.inject.Inject

class ReviewInteractor @Inject constructor(
    private val reviewRepository: ReviewRepository
) {

    suspend fun getUserReviews(): List<ReviewResponse> {
        return reviewRepository.getUserReviews()
    }

    suspend fun getReviews(id: String): List<ReviewResponse> {
        return reviewRepository.getReviews(id)
    }

    suspend fun updateReview(reviewId: String, points: Double, comment: String?) {
        reviewRepository.updateReview(reviewId, ReviewRequest(comment, points))
    }

    suspend fun removeReview(reviewId: String) {
        reviewRepository.removeReview(reviewId)
    }

    suspend fun sendReview(bookId: String, points: Double, comment: String?) {
        reviewRepository.sendReview(bookId, ReviewRequest(comment, points))
    }

}