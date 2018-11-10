package szarch.bme.hu.ibdb.network.repository

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.network.api.CategoryApi
import szarch.bme.hu.ibdb.network.models.category.CategoryResponse
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val categoryApi: CategoryApi
) {
    suspend fun getCategories(): List<CategoryResponse> = withContext(Contexts.NETWORK) {
        return@withContext emptyList<CategoryResponse>()
    }

    suspend fun addCategory(categoryName: String) = withContext(Contexts.NETWORK) {

    }

    suspend fun removeCategory(id: String) = withContext(Contexts.NETWORK) {

    }
}