package szarch.bme.hu.ibdb.ui.users

import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.AdminInteractor
import szarch.bme.hu.ibdb.ui.base.Presenter
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    private val adminInteractor: AdminInteractor
) : Presenter<UsersScreen>() {

    fun getUsers() {
        launch {
            try {
                screen?.showUserList(adminInteractor.getUsers())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun enableUser(userId: String) {
        launch {
            try {
                adminInteractor.enableUser(userId)
                screen?.showSuccessfulMessage()
            } catch (e: Exception) {
                screen?.showErrorMessage()
            }
        }
    }


    fun disableUser(userId: String) {
        launch {
            try {
                adminInteractor.disableUser(userId)
                screen?.showSuccessfulMessage()
            } catch (e: Exception) {
                screen?.showErrorMessage()
            }
        }
    }

    fun removeUser(userId: String) {
        launch {
            try {
                adminInteractor.removeUser(userId)
                screen?.showSuccessfulMessage()
            } catch (e: Exception) {
                screen?.showErrorMessage()
            }
        }
    }


}