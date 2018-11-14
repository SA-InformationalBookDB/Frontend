package szarch.bme.hu.ibdb.ui.users

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.network.repository.AdminRepository
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    private val adminRepository: AdminRepository
) : Presenter<UsersScreen>() {

    fun getUsers() {
        //screen?.showUserList(adminRepository.)
    }

    fun enableUser(userId: String) {
        GlobalScope.launch(Contexts.UI) {
            try {
                adminRepository.enableUser(userId)
            } catch (e: UnauthorizedException) {
                screen?.showErrorMessage()
                e.printStackTrace()
            } catch (e: ForbiddenException) {
                screen?.showErrorMessage()
                e.printStackTrace()
            } catch (e: NotFoundException) {
                screen?.showErrorMessage()
                e.printStackTrace()
            } catch (e: Exception) {
                screen?.showErrorMessage()
                e.printStackTrace()
            }
        }
    }

    fun disableUser(userId: String) {
        GlobalScope.launch(Contexts.UI) {
            try {
                adminRepository.disableUser(userId)
            } catch (e: UnauthorizedException) {
                screen?.showErrorMessage()
                e.printStackTrace()
            } catch (e: ForbiddenException) {
                screen?.showErrorMessage()
                e.printStackTrace()
            } catch (e: NotFoundException) {
                screen?.showErrorMessage()
                e.printStackTrace()
            } catch (e: Exception) {
                screen?.showErrorMessage()
                e.printStackTrace()
            }
        }
    }

    fun removeUser(userId: String) {
        GlobalScope.launch(Contexts.UI) {
            try {
                adminRepository.removeUser(userId)
            } catch (e: UnauthorizedException) {
                screen?.showErrorMessage()
                e.printStackTrace()
            } catch (e: ForbiddenException) {
                screen?.showErrorMessage()
                e.printStackTrace()
            } catch (e: NotFoundException) {
                screen?.showErrorMessage()
                e.printStackTrace()
            } catch (e: Exception) {
                screen?.showErrorMessage()
                e.printStackTrace()
            }
        }
    }

}