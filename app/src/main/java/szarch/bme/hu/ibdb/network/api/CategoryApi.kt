package szarch.bme.hu.ibdb.network.api

import retrofit2.Call
import retrofit2.http.*
import szarch.bme.hu.ibdb.network.models.category.CategoryResponse

interface CategoryApi {

    @GET("/category")
    fun getCategories(): Call<List<CategoryResponse>>

    @POST("/admin/category")
    fun addCategory(@Query("name") name: String)

    @DELETE("/admin/category/{id}")
    fun removeCategory(@Path("id") id: String)


}