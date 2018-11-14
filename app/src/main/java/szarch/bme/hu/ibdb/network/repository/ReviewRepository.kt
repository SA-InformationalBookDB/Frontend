package szarch.bme.hu.ibdb.network.repository

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.network.api.ReviewApi
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.network.models.review.ReviewRequest
import szarch.bme.hu.ibdb.network.models.review.ReviewResponse
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewRepository @Inject constructor(
    private val reviewApi: ReviewApi
) {

    suspend fun getUserReviews(): List<ReviewResponse> = withContext(Contexts.NETWORK) {
        val response = reviewApi.getUserReviews().execute()
        if (response.isSuccessful) {
            return@withContext response.body()!!
        } else {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

    suspend fun getReviews(id: String) = withContext(Contexts.NETWORK) {
        val response = reviewApi.getBookReviews(id).execute()
        if (response.isSuccessful) {
            return@withContext response.body()!!
        } else {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

    suspend fun updateReview(reviewId: String, reviewRequest: ReviewRequest) = withContext(Contexts.NETWORK) {
        val response = reviewApi.updateReview(reviewId, reviewRequest).execute()
        if (response.isSuccessful.not()) {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

    suspend fun removeReview(reviewId: String) = withContext(Contexts.NETWORK) {
        val response = reviewApi.removeReview(reviewId).execute()
        if (response.isSuccessful.not()) {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

    suspend fun sendReview(bookId: String, reviewRequest: ReviewRequest) = withContext(Contexts.NETWORK) {
        val response = reviewApi.sendReview(bookId, reviewRequest).execute()
        if (response.isSuccessful.not()) {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

}