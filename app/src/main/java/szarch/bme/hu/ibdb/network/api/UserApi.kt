package szarch.bme.hu.ibdb.network.api

import retrofit2.Call
import retrofit2.http.*
import szarch.bme.hu.ibdb.network.models.book.BookResponse
import szarch.bme.hu.ibdb.network.models.user.CategoriesUpdateRequest
import szarch.bme.hu.ibdb.network.models.user.UpdateUserRequest
import szarch.bme.hu.ibdb.network.models.user.UserInfoResponse

interface UserApi {

    @GET("/user/favourite")
    fun getFavourites(): Call<List<BookResponse>>

    @PUT("/user/category")
    fun updateCategories(
        @Query("userId") userId: String,
        @Body categoriesUpdateRequest: CategoriesUpdateRequest
    ): Call<Void>

    @PUT("/user/favourite/{id}")
    fun addCategory(
        @Path("id") favouriteId: String,
        @Query("userId") userId: String
    ): Call<Void>

    @DELETE("/user/favourite/{id}")
    fun deleteCategory(
        @Path("id") favouriteId: String,
        @Query("userId") userId: String
    ): Call<Void>

    @GET("/user/profile")
    fun getUserInfo(): Call<UserInfoResponse>

    @PUT("/user/profile")
    fun updateUserInfo(
        @Query("userId") userId: String,
        @Body updateUserRequest: UpdateUserRequest
    )

    @DELETE("/user/profile")
    fun deleteUser(
        @Query("userId") userId: String,
        @Body updateUserRequest: UpdateUserRequest
    )

}