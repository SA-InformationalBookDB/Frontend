package szarch.bme.hu.ibdb.domain.interactors

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.network.models.review.ReviewRequest
import szarch.bme.hu.ibdb.network.models.review.ReviewResponse
import szarch.bme.hu.ibdb.network.repository.ReviewRepository
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class ReviewInteractor @Inject constructor(
    private val reviewRepository: ReviewRepository
) {

    suspend fun getUserReviews(): List<ReviewResponse> = withContext(Contexts.UI) {
        return@withContext reviewRepository.getUserReviews()
    }

    suspend fun getReviews(id: String): List<ReviewResponse> = withContext(Contexts.NETWORK) {
        return@withContext reviewRepository.getReviews(id)
    }

    suspend fun updateReview(reviewId: String, points: Double, comment: String?) = withContext(Contexts.UI) {
        reviewRepository.updateReview(reviewId, ReviewRequest(comment, points))
    }

    suspend fun removeReview(reviewId: String) = withContext(Contexts.UI) {
        reviewRepository.removeReview(reviewId)
    }

    suspend fun sendReview(bookId: String, points: Double, comment: String?) = withContext(Contexts.UI) {
        reviewRepository.sendReview(bookId, ReviewRequest(comment, points))
    }

}