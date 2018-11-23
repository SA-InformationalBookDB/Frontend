package szarch.bme.hu.ibdb.domain.interactors

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.domain.local.SharedPreferencesProvider
import szarch.bme.hu.ibdb.network.models.book.BookResponse
import szarch.bme.hu.ibdb.network.models.user.CategoriesUpdateRequest
import szarch.bme.hu.ibdb.network.models.user.UpdateUserRequest
import szarch.bme.hu.ibdb.network.models.user.UserInfoResponse
import szarch.bme.hu.ibdb.network.repository.UserRepository
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val sharedPreferencesProvider: SharedPreferencesProvider,
    private val userRepository: UserRepository
) {

    suspend fun getFavourites(): List<BookResponse> {
        return userRepository.getFavourites()
    }

    suspend fun addFavourite(bookId: String) {
        return userRepository.addFavourite(bookId)
    }

    suspend fun removeFavourite(bookId: String) {
        return userRepository.deleteFavourite(bookId)
    }

    suspend fun updateCategories(categoryIds: List<String>) {
        userRepository.updateCategories(CategoriesUpdateRequest(categoryIds))
    }

    suspend fun getUserInfo(): UserInfoResponse {
        val userResponse = userRepository.getUserInfo()
        sharedPreferencesProvider.setUserRole(userResponse.role)
        return userResponse
    }

    suspend fun getUserAuthentication(): Boolean {
        return sharedPreferencesProvider.getClientAccessToken().isNotEmpty()
    }

    suspend fun getUserIsAdmin(): Boolean {
        return sharedPreferencesProvider.getUserRole()
    }

    suspend fun updateUserInfo(nickname: String?, birthDate: String?) = withContext(Contexts.UI) {
        return@withContext userRepository.updateUserInfo(UpdateUserRequest(birthDate = birthDate, nickname = nickname))
    }


}