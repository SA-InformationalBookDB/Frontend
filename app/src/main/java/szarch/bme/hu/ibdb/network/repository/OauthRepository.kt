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
}