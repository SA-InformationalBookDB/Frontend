package szarch.bme.hu.ibdb.domain.local

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesProvider @Inject constructor(
    private val context: Context
) {

    private val CLIENT_DATA_STRING = "ibdb_client"
    private val CLIENT_ID_STRING = "client_id"
    private val CLIENT_CODE_STRING = "client_code"
    private val CLIENT_EMAIL_STRING = "client_email"
    private val CLIENT_ACCESS_TOKEN_STRING = "client_access_token"
    private val CLIENT_REFRESH_TOKEN_STRING = "client_refresh_token"
    private val CLIENT_REDIRECT_URI_STRING = "client_redirect_uri"

    private fun setClientId(clientId: String) {
        val sharedPreferences = context.getSharedPreferences(CLIENT_DATA_STRING, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(CLIENT_ID_STRING, clientId).apply()
    }

    private fun getClientId() {
        context.getSharedPreferences(CLIENT_DATA_STRING, Context.MODE_PRIVATE).getString(CLIENT_ID_STRING, "")
    }

    private fun setClientCode(clientCode: String) {
        val sharedPreferences = context.getSharedPreferences(CLIENT_DATA_STRING, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(CLIENT_CODE_STRING, clientCode).apply()
    }

    private fun getClientCode() {
        context.getSharedPreferences(CLIENT_DATA_STRING, Context.MODE_PRIVATE).getString(CLIENT_CODE_STRING, "")
    }

    private fun setClientEmail(clientEmail: String) {
        val sharedPreferences = context.getSharedPreferences(CLIENT_DATA_STRING, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(CLIENT_EMAIL_STRING, clientEmail).apply()
    }

    private fun getClientEmail() {
        context.getSharedPreferences(CLIENT_DATA_STRING, Context.MODE_PRIVATE).getString(CLIENT_EMAIL_STRING, "")
    }

    private fun setClientRefreshToken(refreshToken: String) {
        val sharedPreferences = context.getSharedPreferences(CLIENT_DATA_STRING, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(CLIENT_REFRESH_TOKEN_STRING, refreshToken).apply()
    }

    private fun getClientRefreshToken() {
        context.getSharedPreferences(CLIENT_DATA_STRING, Context.MODE_PRIVATE)
            .getString(CLIENT_REFRESH_TOKEN_STRING, "")
    }

    private fun setClientAccessToken(redirectUri: String) {
        val sharedPreferences = context.getSharedPreferences(CLIENT_DATA_STRING, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(CLIENT_ACCESS_TOKEN_STRING, redirectUri).apply()
    }

    private fun getClientAccessToken() {
        context.getSharedPreferences(CLIENT_DATA_STRING, Context.MODE_PRIVATE).getString(CLIENT_ACCESS_TOKEN_STRING, "")
    }

    private fun setClientRedirectUri(redirectUri: String) {
        val sharedPreferences = context.getSharedPreferences(CLIENT_DATA_STRING, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(CLIENT_REDIRECT_URI_STRING, redirectUri).apply()
    }

    private fun getClientRedirectUri() {
        context.getSharedPreferences(CLIENT_DATA_STRING, Context.MODE_PRIVATE).getString(CLIENT_REDIRECT_URI_STRING, "")
    }

}