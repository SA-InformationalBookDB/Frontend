package szarch.bme.hu.ibdb.domain.interactors

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.domain.local.SharedPreferencesProvider
import szarch.bme.hu.ibdb.network.models.category.CategoryResponse
import szarch.bme.hu.ibdb.network.repository.CategoryRepository
import szarch.bme.hu.ibdb.network.repository.UserRepository
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class CategoryInteractor @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository,
    private val sharedPreferencesProvider: SharedPreferencesProvider
) {

    suspend fun getCategories(): List<CategoryResponse> {
        return withContext(Contexts.UI) {
            return@withContext categoryRepository.getCategories()
        }
    }

}