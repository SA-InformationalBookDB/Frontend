package szarch.bme.hu.ibdb.network.api

import retrofit2.Call
import retrofit2.http.*
import szarch.bme.hu.ibdb.network.models.book.BookResponse
import szarch.bme.hu.ibdb.network.models.user.CategoriesUpdateRequest
import szarch.bme.hu.ibdb.network.models.user.UserInfoResponse

interface UserApi {

    @GET("/user/favourite")
    fun getFavourites(): Call<List<BookResponse>>

    @PUT("/user/category")
    fun updateCategories(
        @Body categoriesUpdateRequest: CategoriesUpdateRequest
    ): Call<Void>

    @PUT("/user/favourite/{id}")
    fun addFavourite(
        @Path("id") favouriteId: String
    ): Call<Void>

    @DELETE("/user/favourite/{id}")
    fun deleteFavourite(
        @Path("id") favouriteId: String
    ): Call<Void>

    @GET("/user/profile")
    fun getUserInfo(): Call<UserInfoResponse>

    @PUT("/user/profile")
    fun updateUserInfo(
        @Query("birthDate") birthDate: String?,
        @Query("nickname") nickname: String?
    ): Call<Void>

    @DELETE("/user/profile")
    fun deleteUser(): Call<Void>

}