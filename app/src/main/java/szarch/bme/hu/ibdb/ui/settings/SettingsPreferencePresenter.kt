package szarch.bme.hu.ibdb.ui.settings

import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.CategoryInteractor
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
import szarch.bme.hu.ibdb.ui.base.Presenter
import javax.inject.Inject

class SettingsPreferencePresenter @Inject constructor(
    private val categoryInteractor: CategoryInteractor,
    private val userInteractor: UserInteractor
) : Presenter<SettingsPreferenceScreen>() {

    fun updateUserCategories(categoryIds: List<String>) {
        launch {
            try {
                userInteractor.updateCategories(categoryIds)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getCategories() {
        launch {
            try {
                screen?.showCategoryDialog(categoryInteractor.getCategories())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateUserInfos(nickName: String? = null, birthDate: String? = null) {
        launch {
            try {
                userInteractor.updateUserInfo(nickName, birthDate)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}