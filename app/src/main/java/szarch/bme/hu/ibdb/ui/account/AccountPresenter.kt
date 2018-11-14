package szarch.bme.hu.ibdb.ui.account

import android.content.res.Resources
import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
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
        GlobalScope.launch(Contexts.UI + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> {
                    throwable.printStackTrace()
                    screen?.showRegistrationResult(
                        AuthenticationResult(
                            false,
                            resources.getString(R.string.registration_unsuccessful),
                            throwable.message!!
                        )
                    )
                }
                is ForbiddenException -> {
                    throwable.printStackTrace()
                    screen?.showRegistrationResult(
                        AuthenticationResult(
                            false,
                            resources.getString(R.string.registration_unsuccessful),
                            throwable.message!!
                        )
                    )
                }
                is NotFoundException -> {
                    throwable.printStackTrace()
                    screen?.showRegistrationResult(
                        AuthenticationResult(
                            false,
                            resources.getString(R.string.registration_unsuccessful),
                            throwable.message!!
                        )
                    )
                }
                else -> throwable.printStackTrace()
            }
        }) {
            oauthInteractor.sendRegistrationRequest(email, password, confirmPassword)
            screen?.showRegistrationResult(
                AuthenticationResult(
                    true,
                    resources.getString(R.string.registration_successful)
                )
            )
        }
    }

    fun loginUser(email: String, password: String) {
        GlobalScope.launch(Contexts.UI + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> {
                    throwable.printStackTrace()
                    screen?.showRegistrationResult(
                        AuthenticationResult(
                            false,
                            resources.getString(R.string.login_unsuccessful),
                            throwable.message!!
                        )
                    )
                }
                is ForbiddenException -> {
                    throwable.printStackTrace()
                    screen?.showRegistrationResult(
                        AuthenticationResult(
                            false,
                            resources.getString(R.string.login_unsuccessful),
                            throwable.message!!
                        )
                    )
                }
                is NotFoundException -> {
                    throwable.printStackTrace()
                    screen?.showRegistrationResult(
                        AuthenticationResult(
                            false,
                            resources.getString(R.string.login_unsuccessful),
                            throwable.message!!
                        )
                    )
                }
            }
        })
        {
            oauthInteractor.sendLoginRequest(email, password)
            oauthInteractor.sendAccessTokenRequest()
            screen?.showLoginResult(
                AuthenticationResult(
                    true,
                    resources.getString(R.string.login_successful)
                )
            )
        }
    }


    fun logoutUser() {
        GlobalScope.launch(Contexts.UI + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> {
                    throwable.printStackTrace()
                    screen?.showRegistrationResult(
                        AuthenticationResult(
                            false,
                            resources.getString(R.string.registration_unsuccessful),
                            throwable.message!!
                        )
                    )
                }
                is ForbiddenException -> {
                    throwable.printStackTrace()
                    screen?.showRegistrationResult(
                        AuthenticationResult(
                            false,
                            resources.getString(R.string.registration_unsuccessful),
                            throwable.message!!
                        )
                    )
                }
                is NotFoundException -> {
                    throwable.printStackTrace()
                    screen?.showRegistrationResult(
                        AuthenticationResult(
                            false,
                            resources.getString(R.string.registration_unsuccessful),
                            throwable.message!!
                        )
                    )
                }
                else -> throwable.printStackTrace()
            }
        }) {
            oauthInteractor.sendLogoutRequest()
            screen?.showRegistrationResult(
                AuthenticationResult(
                    true,
                    resources.getString(R.string.registration_successful)
                )
            )
        }
    }


    fun getUser() {
        GlobalScope.launch(Contexts.UI) {
            val user = userInteractor.getUserInfo()
            Log.d("Testing", user.toString())
        }
    }


}