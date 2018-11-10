package szarch.bme.hu.ibdb.network.api

import retrofit2.Call
import retrofit2.http.*
import szarch.bme.hu.ibdb.network.models.base.PageResponseBase
import szarch.bme.hu.ibdb.network.models.book.BookRequest
import szarch.bme.hu.ibdb.network.models.book.BookResponse

interface BookApi {

    @GET("/book/popular")
    fun getPopularBooks()

    @GET("/book/bestseller")
    fun getBestsellerBooks(
        @Field("page") page: Int,
        @Field("size") size: Int
    ): Call<PageResponseBase<BookResponse>>

    @GET("/book/offer")
    fun getOfferBooks(
        @Field("page") page: Int,
        @Field("size") size: Int
    ): Call<PageResponseBase<BookResponse>>

    @GET("/book/trending")
    fun getTrendingBooks(
        @Field("page") page: Int,
        @Field("size") size: Int
    ): Call<PageResponseBase<BookResponse>>

    @GET("/book/{id}")
    fun getBook(@Path("id") bookId: String): Call<BookResponse>

    @POST("/book/find")
    fun findBooks(
        @Field("page") page: Int,
        @Field("size") size: Int
    ): Call<PageResponseBase<BookResponse>>

    @POST("/admin/book")
    fun addBook(@Body bookRequest: BookRequest): Call<Void>

    @PUT("/admin/book/{id}")
    fun updateBook(@Path("id") id: String, @Body bookRequest: BookRequest): Call<Void>

    @DELETE("/admin/book/{id}")
    fun deleteBook(@Path("id") id: String, @Body bookRequest: BookRequest): Call<Void>

}