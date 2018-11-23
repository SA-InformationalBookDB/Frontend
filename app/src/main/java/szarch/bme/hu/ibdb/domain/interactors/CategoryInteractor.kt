package szarch.bme.hu.ibdb.domain.interactors

import szarch.bme.hu.ibdb.network.models.category.CategoryResponse
import szarch.bme.hu.ibdb.network.repository.CategoryRepository
import javax.inject.Inject

class CategoryInteractor @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend fun getCategories(): List<CategoryResponse> {
        return categoryRepository.getCategories()
    }


}