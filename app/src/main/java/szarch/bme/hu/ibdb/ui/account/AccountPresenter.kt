package szarch.bme.hu.ibdb.ui.account

import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
import szarch.bme.hu.ibdb.ui.base.Presenter
import javax.inject.Inject

class AccountPresenter @Inject constructor(
    private val userInteractor: UserInteractor
) : Presenter<AccountScreen>() {

    fun registerUser(username: String, password: String, confirmPassword: String) {

    }

    fun loginUser(username: String, password: String) {

    }

    fun logoutUser() {

    }

}