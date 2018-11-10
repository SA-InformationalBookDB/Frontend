package szarch.bme.hu.ibdb.network.repository

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.network.api.ReviewApi
import szarch.bme.hu.ibdb.network.models.review.ReviewRequest
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewRepository @Inject constructor(
    private val reviewApi: ReviewApi
) {

    suspend fun getUserReviews(page: Int, size: Int) = withContext(Contexts.NETWORK) {

    }

    suspend fun getReviews(id: Int, userId: String) = withContext(Contexts.NETWORK) {

    }

    suspend fun updateReview(reviewId: Int, reviewRequest: ReviewRequest) = withContext(Contexts.NETWORK) {

    }

    suspend fun removeReview(reviewId: Int, userId: String) = withContext(Contexts.NETWORK) {

    }

    suspend fun sendReview(id: Int, userId: String, reviewRequest: ReviewRequest) = withContext(Contexts.NETWORK) {

    }

    suspend fun deleteReviewByAdmin(userId: String, reviewId: Int) = withContext(Contexts.NETWORK) {

    }

}