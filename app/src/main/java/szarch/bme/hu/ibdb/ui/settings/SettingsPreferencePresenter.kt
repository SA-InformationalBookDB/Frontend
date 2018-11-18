package szarch.bme.hu.ibdb.ui.settings

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.CategoryInteractor
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class SettingsPreferencePresenter @Inject constructor(
    private val categoryInteractor: CategoryInteractor,
    private val userInteractor: UserInteractor
) : Presenter<SettingsPreferenceScreen>() {

    fun updateUserCategories(categoryIds: List<String>) {
        GlobalScope.launch(Contexts.UI) {
            userInteractor.updateCategories(categoryIds)
        }
    }

    fun getCategories() {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
        }) {
            screen?.showCategoryDialog(categoryInteractor.getCategories())
        }
    }

    fun updateUserInfos(nickName: String? = null, birthDate: String? = null) {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
        }) {
            userInteractor.updateUserInfo(nickName, birthDate)

        }

    }


}