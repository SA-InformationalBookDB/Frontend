package szarch.bme.hu.ibdb.network.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import szarch.bme.hu.ibdb.network.models.oauth.*

interface OauthApi {

    @POST("/oauth/login")
    fun login(loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/oauth/logout")
    fun logout(logoutRequest: LogoutRequest): Call<Void>

    @POST("/oauth/register")
    fun registrate(registrationRequest: RegistrationRequest): Call<RegistrationResponse>

    @FormUrlEncoded
    @POST("/oauth/token")
    fun refreshToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("code") code: String,
        @Field("refresh_token") refreshToken: String
    ): Call<AccessTokenResponse>

}