package szarch.bme.hu.ibdb.network.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import szarch.bme.hu.ibdb.network.models.book.BookResponse

interface BookApi {

    @GET("/book/popular")
    fun getPopularBooks(
    ): Call<List<BookResponse>>

    @GET("/book/bestseller")
    fun getBestsellerBooks(
    ): Call<List<BookResponse>>

    @GET("/book/offer")
    fun getOfferBooks(
    ): Call<List<BookResponse>>

    @GET("/book/trending")
    fun getTrendingBooks(
    ): Call<List<BookResponse>>

    @GET("/book/{id}")
    fun getBook(@Path("id") bookId: String): Call<BookResponse>

    @POST("/book/find")
    fun findBooks(
        @Field("queryString") queryString: String
    ): Call<List<BookResponse>>

    @GET("/public/book/bestseller")
    fun getPublicBestseller(): Call<List<BookResponse>>

    @GET("/public/book/popular")
    fun getPublicPopularBooks(): Call<List<BookResponse>>

    @GET("/public/book/trending")
    fun getPublicTrendingBook(): Call<List<BookResponse>>

}