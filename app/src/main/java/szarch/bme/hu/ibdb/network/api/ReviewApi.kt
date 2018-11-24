package szarch.bme.hu.ibdb.network.api

import retrofit2.Call
import retrofit2.http.*
import szarch.bme.hu.ibdb.network.models.review.Review
import szarch.bme.hu.ibdb.network.models.review.ReviewRequest

interface ReviewApi {

    @GET("/review")
    fun getUserReviews(): Call<List<Review>>


    @PUT("/review/{reviewId}")
    fun updateReview(
        @Path("reviewId") reviewId: String,
        @Body reviewRequest: ReviewRequest
    ): Call<Void>

    @DELETE("/review/{reviewId}")
    fun removeReview(
        @Path("reviewId") reviewId: String
    ): Call<Void>

    @POST("/review/book/{id}")
    fun sendReview(
        @Path("id") id: String,
        @Body reviewRequest: ReviewRequest
    ): Call<Void>

    @GET("/review/book/{id}")
    fun getBookReviews(@Path("id") bookId: String): Call<List<Review>>



}