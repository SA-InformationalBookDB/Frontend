package szarch.bme.hu.ibdb.ui.account

import android.content.res.Resources
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.interactors.OauthInteractor
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
import szarch.bme.hu.ibdb.domain.models.AuthenticationResult
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class AccountPresenter @Inject constructor(
    private val userInteractor: UserInteractor,
    private val oauthInteractor: OauthInteractor,
    private val resources: Resources
) : Presenter<AccountScreen>() {

    fun registerUser(email: String, password: String, confirmPassword: String) {
        GlobalScope.launch(Contexts.UI) {
            try {
                oauthInteractor.sendRegistrationRequest(email, password, confirmPassword)
                screen?.showRegistrationResult(
                    AuthenticationResult(
                        true,
                        resources.getString(R.string.registration_successful)
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
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
        GlobalScope.launch(Contexts.UI) {
            try {
                oauthInteractor.sendLoginRequest(email, password)
                oauthInteractor.sendAccessTokenRequest()
                screen?.showLoginResult(
                    AuthenticationResult(
                        true,
                        resources.getString(R.string.login_successful)
                    )
                )
            } catch (e: UnauthorizedException) {
                e.printStackTrace()
                screen?.showLoginResult(
                    AuthenticationResult(
                        false,
                        resources.getString(R.string.login_unsuccessful),
                        e.message!!
                    )
                )
            } catch (e: ForbiddenException) {
                e.printStackTrace()
                screen?.showLoginResult(
                    AuthenticationResult(
                        false,
                        resources.getString(R.string.login_unsuccessful),
                        e.message!!
                    )
                )
            } catch (e: NotFoundException) {
                e.printStackTrace()
                screen?.showLoginResult(
                    AuthenticationResult(
                        false,
                        resources.getString(R.string.login_unsuccessful),
                        e.message!!
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
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
        GlobalScope.launch(Contexts.UI) {
            try {
                oauthInteractor.sendLogoutRequest()
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

    fun getUser() {
        GlobalScope.launch(Contexts.UI) {
            val user = userInteractor.getUserInfo()
            Log.d("Testing", user.toString())
        }
    }


}