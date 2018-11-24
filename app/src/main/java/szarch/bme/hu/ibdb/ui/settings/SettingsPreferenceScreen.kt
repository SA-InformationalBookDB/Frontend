package szarch.bme.hu.ibdb.ui.settings

import szarch.bme.hu.ibdb.network.models.category.Category

interface SettingsPreferenceScreen {
    fun showCategoryDialog(categoryList: List<Category>)
}