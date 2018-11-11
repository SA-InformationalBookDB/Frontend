package szarch.bme.hu.ibdb.domain.interactors

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.domain.local.SharedPreferencesProvider
import szarch.bme.hu.ibdb.network.models.oauth.LoginRequest
import szarch.bme.hu.ibdb.network.models.oauth.LogoutRequest
import szarch.bme.hu.ibdb.network.models.oauth.RegistrationRequest
import szarch.bme.hu.ibdb.network.repository.OauthRepository
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class OauthInteractor @Inject constructor(
    private val oauthRepository: OauthRepository,
    private val sharedPreferencesProvider: SharedPreferencesProvider
) {
    private val accessTokenType: String = "authorization_code"
    private val refreshTokenGrantType: String = "refresh_token"

    suspend fun sendLoginRequest(email: String, password: String) =
        withContext(Contexts.UI) {
            val loginResponse =
                oauthRepository.sendLogin(
                    LoginRequest(
                        email = email,
                        password = password,
                        clientId = sharedPreferencesProvider.getClientId(),
                        redirectUri = sharedPreferencesProvider.getClientRedirectUri()
                    )
                )
            sharedPreferencesProvider.setClientCode(loginResponse.code)
        }


    suspend fun sendRegistrationRequest(email: String, password: String, confirmPassword: String) =
        withContext(Contexts.UI) {
            val response = oauthRepository.registrate(RegistrationRequest(email, password, confirmPassword))
            sharedPreferencesProvider.setClientUserId(response.userId)
        }


    suspend fun sendLogoutRequest() = withContext(Contexts.UI) {
        oauthRepository.sendLogout(LogoutRequest(sharedPreferencesProvider.getClientAccessToken()))
    }


    suspend fun sendAccessTokenRequest() = withContext(Contexts.UI) {
        val response = oauthRepository.refreshToken(
            grantType = accessTokenType,
            client_id = sharedPreferencesProvider.getClientId(),
            redirect_uri = sharedPreferencesProvider.getClientRedirectUri(),
            code = sharedPreferencesProvider.getClientCode()
        )
        sharedPreferencesProvider.setClientAccessToken(response.accessToken)
        sharedPreferencesProvider.setClientRefreshToken(response.refreshToken)
    }

    suspend fun sendRefreshTokenRequest() = withContext(Contexts.UI) {
        val response = oauthRepository.refreshToken(
            grantType = refreshTokenGrantType,
            client_id = sharedPreferencesProvider.getClientId(),
            redirect_uri = sharedPreferencesProvider.getClientRedirectUri(),
            code = sharedPreferencesProvider.getClientCode()
        )
        sharedPreferencesProvider.setClientAccessToken(response.accessToken)
        sharedPreferencesProvider.setClientRefreshToken(response.refreshToken)
    }

}
