package szarch.bme.hu.ibdb.network.repository

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.network.api.OauthApi
import szarch.bme.hu.ibdb.network.models.oauth.LoginRequest
import szarch.bme.hu.ibdb.network.models.oauth.LoginResponse
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OauthRepository @Inject constructor(
    private val oauthApi: OauthApi
) {

    suspend fun sendLogin(loginRequest: LoginRequest): LoginResponse? = withContext(Contexts.NETWORK) {
        val response = oauthApi.login(loginRequest).execute()
        if (response.isSuccessful) {
            return@withContext response.body()!!
        } else {
            null
        }
    }

    suspend fun sendLogout() = withContext(Contexts.NETWORK) {

    }

    /*
    suspend fun registrate():RegistrationResponse = withContext(Contexts.NETWORK){

    }

    suspend fun refreshToken(
        grantType:String,
        client_id:String,
        redirect_uri:String,
        code:String,
        refresh_token:String
    ):AccessTokenResponse = withContext(Contexts.NETWORK){

    }*/
}