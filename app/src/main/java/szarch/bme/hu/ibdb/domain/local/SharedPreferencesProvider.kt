package szarch.bme.hu.ibdb.domain.local

import android.content.Context
import szarch.bme.hu.ibdb.network.models.user.Role
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesProvider @Inject constructor(
    context: Context
) {

    private val CLIENT_DATA_STRING = "ibdb_client"
    private val CLIENT_ID_STRING = "client_id"
    private val CLIENT_USER_ID_STRING = "client_user_id"
    private val CLIENT_CODE_STRING = "client_code"
    private val CLIENT_EMAIL_STRING = "client_email"
    private val CLIENT_ACCESS_TOKEN_STRING = "client_access_token"
    private val CLIENT_REFRESH_TOKEN_STRING = "client_refresh_token"
    private val CLIENT_REDIRECT_URI_STRING = "client_redirect_uri"
    private val CLIENT_FIRST_STARTING = "client_first_starting"
    private val CLIENT_IS_ADMIN = "client_role"

    private val sharedPreferences = context.getSharedPreferences(CLIENT_DATA_STRING, Context.MODE_PRIVATE)

    fun setClientId(clientId: String) {
        sharedPreferences.edit().putString(CLIENT_ID_STRING, clientId).apply()
    }

    fun getClientId(): String = sharedPreferences.getString(CLIENT_ID_STRING, "73281642736")!!

    fun setClientUserId(userId: String) {
        sharedPreferences.edit().putString(CLIENT_USER_ID_STRING, userId).apply()
    }

    fun getClinetUserId() = sharedPreferences.getString(CLIENT_USER_ID_STRING, "")!!

    fun setClientCode(clientCode: String) {
        sharedPreferences.edit().putString(CLIENT_CODE_STRING, clientCode).apply()
    }

    fun getClientCode(): String = sharedPreferences.getString(CLIENT_CODE_STRING, "")!!


    fun setClientEmail(clientEmail: String) {
        sharedPreferences.edit().putString(CLIENT_EMAIL_STRING, clientEmail).apply()
    }

    fun getClientEmail(): String = sharedPreferences.getString(CLIENT_EMAIL_STRING, "")!!


    fun setClientRefreshToken(refreshToken: String) {
        sharedPreferences.edit().putString(CLIENT_REFRESH_TOKEN_STRING, refreshToken).apply()
    }

    fun getClientRefreshToken(): String = sharedPreferences.getString(CLIENT_REFRESH_TOKEN_STRING, "")!!


    fun setClientAccessToken(accessToken: String) {
        sharedPreferences.edit().putString(CLIENT_ACCESS_TOKEN_STRING, accessToken).apply()
    }

    fun getClientAccessToken(): String = sharedPreferences.getString(CLIENT_ACCESS_TOKEN_STRING, "")!!


    fun setClientRedirectUri(redirectUri: String) {
        sharedPreferences.edit().putString(CLIENT_REDIRECT_URI_STRING, redirectUri).apply()
    }

    fun getClientRedirectUri(): String = sharedPreferences.getString(CLIENT_REDIRECT_URI_STRING, "redirect_uri")!!


    fun setClientFirstStarting(isFirstStarting: Boolean) {
        sharedPreferences.edit().putBoolean(CLIENT_FIRST_STARTING, isFirstStarting).apply()
    }

    fun getIsClientFirstStarting(): Boolean = sharedPreferences.getBoolean(CLIENT_FIRST_STARTING, true)

    fun clearUserDatas() {
        sharedPreferences.edit().putString(CLIENT_USER_ID_STRING, "").apply()
        sharedPreferences.edit().putString(CLIENT_CODE_STRING, "").apply()
        sharedPreferences.edit().putString(CLIENT_EMAIL_STRING, "").apply()
        sharedPreferences.edit().putString(CLIENT_REFRESH_TOKEN_STRING, "").apply()
        sharedPreferences.edit().putString(CLIENT_ACCESS_TOKEN_STRING, "").apply()
        sharedPreferences.edit().putString(CLIENT_REDIRECT_URI_STRING, "").apply()
    }

    fun setUserRole(role: Role) {
        sharedPreferences.edit().putBoolean(CLIENT_IS_ADMIN, role == Role.ADMIN).apply()
    }

    fun getUserRole(): Boolean {
        return sharedPreferences.getBoolean(CLIENT_IS_ADMIN, false)
    }


}