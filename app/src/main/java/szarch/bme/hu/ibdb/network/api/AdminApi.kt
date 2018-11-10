package szarch.bme.hu.ibdb.network.api

import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface AdminApi {

    @POST("/admin/{userId}/disable")
    fun disableUser(@Path("userId") userId: String)

    @POST("/admin/{userId}/enable")
    fun enableUser(@Path("userId") userId: String)

    @DELETE("/admin/{userId}/remove")
    fun removeUser(@Path("userId") userId: String)

}