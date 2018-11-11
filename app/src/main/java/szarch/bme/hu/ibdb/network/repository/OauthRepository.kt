package szarch.bme.hu.ibdb.network.repository

import android.util.Log
import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.network.api.OauthApi
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.network.models.oauth.*
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OauthRepository @Inject constructor(
    private val oauthApi: OauthApi
) {

    suspend fun sendLogin(loginRequest: LoginRequest): LoginResponse = withContext(Contexts.NETWORK) {
        val response = oauthApi.login(loginRequest).execute()
        if (response.isSuccessful) {
            return@withContext response.body()!!
        } else {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

    suspend fun sendLogout(logoutRequest: LogoutRequest) = withContext(Contexts.NETWORK) {
        val response = oauthApi.logout(logoutRequest).execute()
        if (!response.isSuccessful) {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }


    suspend fun registrate(registrationRequest: RegistrationRequest): RegistrationResponse =
        withContext(Contexts.NETWORK) {
            Log.d("Testing", oauthApi.toString())
            val response = oauthApi.registrate(registrationRequest).execute()
            if (response.isSuccessful) {
                return@withContext response.body()!!
            } else {
                when (response.code()) {
                    401 -> throw UnauthorizedException("Unauthorized")
                    403 -> throw ForbiddenException("Forbidden")
                    404 -> throw NotFoundException("Not found")
                    else -> throw Exception(response.message())
                }
            }
        }

    suspend fun refreshToken(
        grantType: String,
        client_id: String,
        redirect_uri: String,
        code: String? = null,
        refresh_token: String? = null
    ): AccessTokenResponse = withContext(Contexts.NETWORK) {
        val response = oauthApi.refreshToken(
            grantType = grantType,
            clientId = client_id,
            redirectUri = redirect_uri,
            code = code,
            refreshToken = refresh_token
        ).execute()
        if (response.isSuccessful) {
            return@withContext response.body()!!
        } else {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }
}