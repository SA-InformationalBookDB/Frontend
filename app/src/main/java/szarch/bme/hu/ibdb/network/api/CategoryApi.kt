package szarch.bme.hu.ibdb.network.api

import retrofit2.Call
import retrofit2.http.*
import szarch.bme.hu.ibdb.network.models.category.Category

interface CategoryApi {

    @GET("/category")
    fun getCategories(): Call<List<Category>>

    @POST("/admin/category")
    fun addCategory(@Query("name") name: String)

    @DELETE("/admin/category/{id}")
    fun removeCategory(@Path("id") id: String)


}