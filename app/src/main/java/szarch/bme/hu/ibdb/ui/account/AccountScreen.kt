package szarch.bme.hu.ibdb.ui.account

import szarch.bme.hu.ibdb.domain.models.AuthenticationResult

interface AccountScreen {

    fun showRegistrationResult(authenticationResult: AuthenticationResult)
    fun showLoginResult(authenticationResult: AuthenticationResult)
    fun showIsUserAuthenticated(isUserAuthenticated: Boolean)
    fun showLogoutResult(authenticationResult: AuthenticationResult)
}