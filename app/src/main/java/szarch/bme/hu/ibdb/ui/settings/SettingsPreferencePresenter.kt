package szarch.bme.hu.ibdb.ui.settings

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.CategoryInteractor
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
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

}