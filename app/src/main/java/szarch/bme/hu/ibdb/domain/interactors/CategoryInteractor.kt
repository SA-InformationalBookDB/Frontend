package szarch.bme.hu.ibdb.domain.interactors

import szarch.bme.hu.ibdb.network.models.category.Category
import szarch.bme.hu.ibdb.network.repository.CategoryRepository
import javax.inject.Inject

class CategoryInteractor @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend fun getCategories(): List<Category> {
        return categoryRepository.getCategories()
    }


}