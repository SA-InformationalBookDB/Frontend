package szarch.bme.hu.ibdb.ui.account

import android.content.res.Resources
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.interactors.OauthInteractor
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
import szarch.bme.hu.ibdb.domain.local.SharedPreferencesProvider
import szarch.bme.hu.ibdb.network.models.base.AuthenticationResult
import szarch.bme.hu.ibdb.ui.base.Presenter
import javax.inject.Inject

class AccountPresenter @Inject constructor(
    private val userInteractor: UserInteractor,
    private val oauthInteractor: OauthInteractor,
    private val sharedPreferencesProvider: SharedPreferencesProvider,
    private val resources: Resources
) : Presenter<AccountScreen>() {

    fun registerUser(email: String, password: String, confirmPassword: String) {
        launch {
            try {
                oauthInteractor.sendRegistrationRequest(email, password, confirmPassword)
                screen?.showRegistrationResult(
                    AuthenticationResult(
                        true,
                        resources.getString(R.string.registration_successful)
                    )
                )
            } catch (e: Exception) {
                screen?.showRegistrationResult(
                    AuthenticationResult(
                        false,
                        resources.getString(R.string.registration_unsuccessful),
                        e.message!!
                    )
                )
            }
        }
    }


    fun loginUser(email: String, password: String) {
        launch {
            try {
                oauthInteractor.sendLoginRequest(email, password)
                oauthInteractor.sendAccessTokenRequest()
                screen?.showLoginResult(
                    AuthenticationResult(
                        true,
                        resources.getString(R.string.login_successful)
                    )
                )
            } catch (e: Exception) {
                screen?.showLoginResult(
                    AuthenticationResult(
                        false,
                        resources.getString(R.string.login_unsuccessful),
                        e.message!!
                    )
                )
            }
        }

    }

    fun logoutUser() {
        launch {
            try {
                oauthInteractor.sendLogoutRequest()
                screen?.showLogoutResult(
                    AuthenticationResult(
                        true,
                        resources.getString(R.string.logout_successful)
                    )
                )
            } catch (e: Exception) {
                sharedPreferencesProvider.clearUserDatas()
                screen?.showLogoutResult(
                    AuthenticationResult(
                        false,
                        resources.getString(R.string.logout_unsuccessful),
                        e.message!!
                    )
                )
            } finally {
                screen?.showUserInfos()
            }
        }
    }


    fun getUser() {
        launch {
            try {
                userInteractor.getUserInfo()
                screen?.showUserInfos()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun getUserAuthenticationInfo() {
        launch {
            try {
                screen?.showIsUserAuthenticated(userInteractor.getUserAuthentication())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

}