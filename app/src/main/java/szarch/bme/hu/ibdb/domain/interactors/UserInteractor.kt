package szarch.bme.hu.ibdb.domain.interactors

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.domain.local.SharedPreferencesProvider
import szarch.bme.hu.ibdb.network.models.book.BookResponse
import szarch.bme.hu.ibdb.network.models.user.CategoriesUpdateRequest
import szarch.bme.hu.ibdb.network.repository.UserRepository
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val sharedPreferencesProvider: SharedPreferencesProvider,
    private val userRepository: UserRepository
) {

    suspend fun getFavourites(): List<BookResponse> = withContext(Contexts.UI) {
        return@withContext userRepository.getFavourites()
    }

    suspend fun updateCategories(categoryIds: List<String>) = withContext(Contexts.UI) {
        userRepository.updateCategories(CategoriesUpdateRequest(categoryIds))
    }

    suspend fun addFavourite(bookId: String) {
        userRepository
    }

}