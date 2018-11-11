package szarch.bme.hu.ibdb.network.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import szarch.bme.hu.ibdb.network.models.oauth.*

interface OauthApi {

    @POST("/oauth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/oauth/logout")
    fun logout(@Body logoutRequest: LogoutRequest): Call<Void>

    @POST("/oauth/register")
    fun registrate(@Body registrationRequest: RegistrationRequest): Call<RegistrationResponse>

    @POST("/oauth/token")
    fun refreshToken(
        @Query("client_id") clientId: String,
        @Query("code") code: String?,
        @Query("grant_type") grantType: String,
        @Query("redirect_uri") redirectUri: String,
        @Query("refresh_token") refreshToken: String?
    ): Call<AccessTokenResponse>

}