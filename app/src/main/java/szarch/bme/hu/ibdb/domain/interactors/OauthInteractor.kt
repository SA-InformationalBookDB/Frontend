package szarch.bme.hu.ibdb.domain.interactors

import szarch.bme.hu.ibdb.domain.local.SharedPreferencesProvider
import szarch.bme.hu.ibdb.network.models.oauth.LoginRequest
import szarch.bme.hu.ibdb.network.models.oauth.LogoutRequest
import szarch.bme.hu.ibdb.network.models.oauth.RegistrationRequest
import szarch.bme.hu.ibdb.network.repository.OauthRepository
import javax.inject.Inject

class OauthInteractor @Inject constructor(
    private val oauthRepository: OauthRepository,
    private val sharedPreferencesProvider: SharedPreferencesProvider
) {
    private val accessTokenType: String = "authorization_code"
    private val refreshTokenGrantType: String = "refresh_token"

    suspend fun sendLoginRequest(email: String, password: String) {
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


    suspend fun sendRegistrationRequest(email: String, password: String, confirmPassword: String) {
        val response = oauthRepository.registrate(RegistrationRequest(email, password, confirmPassword))
        sharedPreferencesProvider.setClientUserId(response.userId)
    }


    suspend fun sendLogoutRequest() {
        oauthRepository.sendLogout(LogoutRequest(sharedPreferencesProvider.getClientAccessToken()))
        sharedPreferencesProvider.clearUserDatas()
    }


    suspend fun sendAccessTokenRequest() {
        val response = oauthRepository.refreshToken(
            grantType = accessTokenType,
            client_id = sharedPreferencesProvider.getClientId(),
            redirect_uri = sharedPreferencesProvider.getClientRedirectUri(),
            code = sharedPreferencesProvider.getClientCode()
        )
        sharedPreferencesProvider.setClientAccessToken(response.accessToken)
        sharedPreferencesProvider.setClientRefreshToken(response.refreshToken)
    }

    suspend fun sendRefreshTokenRequest() {
        val response = oauthRepository.refreshToken(
            grantType = refreshTokenGrantType,
            client_id = sharedPreferencesProvider.getClientId(),
            redirect_uri = sharedPreferencesProvider.getClientRedirectUri(),
            code = sharedPreferencesProvider.getClientCode(),
            refresh_token = sharedPreferencesProvider.getClientRefreshToken()
        )
        sharedPreferencesProvider.setClientAccessToken(response.accessToken)
        sharedPreferencesProvider.setClientRefreshToken(response.refreshToken)
    }

}
