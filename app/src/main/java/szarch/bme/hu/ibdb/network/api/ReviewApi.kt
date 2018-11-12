package szarch.bme.hu.ibdb.network.api

import retrofit2.Call
import retrofit2.http.*
import szarch.bme.hu.ibdb.network.models.review.ReviewRequest
import szarch.bme.hu.ibdb.network.models.review.ReviewResponse

interface ReviewApi {

    @GET("/review")
    fun getUserReviews(
        @Query("userId") userId: String
    ): Call<List<ReviewResponse>>

    @GET("/review/{id}")
    fun getReviews(
        @Path("id") id: String,
        @Query("userId") userId: String
    ): Call<List<ReviewResponse>>

    @GET("/review/{reviewId}")
    fun updateReview(
        @Path("reviewId") reviewId: String,
        @Body reviewRequest: ReviewRequest
    ): Call<Void>

    @DELETE("/review/{reviewId}")
    fun removeReview(
        @Path("reviewId") reviewId: String,
        @Query("userId") userId: String
    ): Call<Void>

    @POST("/review/book/{id}")
    fun sendReview(
        @Path("id") id: String,
        @Query("userId") userId: String,
        @Body reviewRequest: ReviewRequest
    ): Call<Void>

    @DELETE("/admin/user/{userId}/review/{reviewId}")
    fun deleteReviewByAdmin(@Path("userId") userId: String, @Path("reviewId") reviewId: String)

}