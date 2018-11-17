package szarch.bme.hu.ibdb.ui.upload

import szarch.bme.hu.ibdb.network.models.category.CategoryResponse

interface UploadScreen {
    fun showUplaodMesaage(message: String)
    fun setCategories(categories: List<CategoryResponse>)
}
