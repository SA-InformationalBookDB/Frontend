package szarch.bme.hu.ibdb.ui.users

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.AdminInteractor
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    private val adminInteractor: AdminInteractor
) : Presenter<UsersScreen>() {

    fun getUsers() {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
        }) {
            screen?.showUserList(adminInteractor.getUsers())
        }

    }

    fun enableUser(userId: String) {
        GlobalScope.launch(Contexts.UI + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> screen?.showErrorMessage()
                is ForbiddenException -> screen?.showErrorMessage()
                is NotFoundException -> screen?.showErrorMessage()
                else -> screen?.showErrorMessage()
            }
        }) {
            adminInteractor.enableUser(userId)
        }
    }


    fun disableUser(userId: String) {
        GlobalScope.launch(Contexts.UI + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> screen?.showErrorMessage()
                is ForbiddenException -> screen?.showErrorMessage()
                is NotFoundException -> screen?.showErrorMessage()
                else -> screen?.showErrorMessage()
            }
        }) {
            adminInteractor.disableUser(userId)
        }
    }

    fun removeUser(userId: String) {
        GlobalScope.launch(Contexts.UI + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> screen?.showErrorMessage()
                is ForbiddenException -> screen?.showErrorMessage()
                is NotFoundException -> screen?.showErrorMessage()
                else -> screen?.showErrorMessage()
            }
        }) {
            adminInteractor.removeUser(userId)
        }
    }


}