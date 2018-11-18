package szarch.bme.hu.ibdb.network.api

import retrofit2.Call
import retrofit2.http.*
import szarch.bme.hu.ibdb.network.models.book.BookRequest
import szarch.bme.hu.ibdb.network.models.user.UserInfoResponse

interface AdminApi {


    @POST("/admin/book")
    fun addBook(@Body bookRequest: BookRequest): Call<Void>

    @PUT("/admin/book/{id}")
    fun updateBook(@Path("id") id: String, @Body bookRequest: BookRequest): Call<Void>

    @DELETE("/admin/book/{id}")
    fun deleteBook(@Path("id") id: String): Call<Void>

    @POST("/admin/{userId}/disable")
    fun disableUser(@Path("userId") userId: String): Call<Void>

    @POST("/admin/{userId}/enable")
    fun enableUser(@Path("userId") userId: String): Call<Void>

    @DELETE("/admin/{userId}/remove")
    fun removeUser(@Path("userId") userId: String): Call<Void>

    @DELETE("/admin/user/{userId}/review/{reviewId}")
    fun deleteReviewByAdmin(@Path("userId") userId: String, @Path("reviewId") reviewId: String): Call<Void>

    @GET("admin/user")
    fun getUsers(): Call<List<UserInfoResponse>>

}