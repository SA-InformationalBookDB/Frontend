package szarch.bme.hu.ibdb.network.api

import retrofit2.http.*
import szarch.bme.hu.ibdb.network.dto.BookRequest

interface adminApi {

    @POST("/admin/{userId}/disable")
    fun disableUser(@Path("userId") userId: String)

    @POST("/admin/{userId}/enable")
    fun enableUser(@Path("userId") userId: String)

    @DELETE("/admin/{userId}/remove")
    fun removeUser(@Path("userId") userId: String)

    //Todo why here and how to remove review
    @DELETE("/admin/{userId}/review/removeReview")
    fun removeReview(@Path("userId") userId: String)

    @POST("/admin/book/addBook")
    fun addBook(@Body book: BookRequest)

    @PUT("/admin/book/{id}")
    fun updateBook(@Path("id") bookId: String, @Body book: BookRequest)

    @DELETE("/admin/book/{id}")
    fun removeBook(@Path("id") bookId: String)

}