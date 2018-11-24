package szarch.bme.hu.ibdb.network.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import szarch.bme.hu.ibdb.network.models.book.Book

interface BookApi {

    @GET("/book/popular")
    fun getPopularBooks(
    ): Call<List<Book>>

    @GET("/book/bestseller")
    fun getBestsellerBooks(
    ): Call<List<Book>>

    @GET("/book/offer")
    fun getOfferBooks(
        @Query("publishedAfter") publishedAfter: String
    ): Call<List<Book>>

    @GET("/book/trending")
    fun getTrendingBooks(@Query("publishedAfter") publishedAfter: String): Call<List<Book>>

    @GET("/book/{id}")
    fun getBook(@Path("id") bookId: String): Call<Book>

    @POST("/book/find")
    fun findBooks(
        @Query("queryString") queryString: String
    ): Call<List<Book>>

    @GET("/public/book/bestseller")
    fun getPublicBestseller(): Call<List<Book>>

    @GET("/public/book/popular")
    fun getPublicPopularBooks(): Call<List<Book>>

    @GET("/public/book/trending")
    fun getPublicTrendingBook(): Call<List<Book>>

}