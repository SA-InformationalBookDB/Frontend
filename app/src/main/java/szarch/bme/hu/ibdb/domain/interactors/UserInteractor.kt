package szarch.bme.hu.ibdb.domain.interactors

import szarch.bme.hu.ibdb.domain.local.SharedPreferencesProvider
import szarch.bme.hu.ibdb.network.models.book.Book
import szarch.bme.hu.ibdb.network.models.user.CategoriesUpdateRequest
import szarch.bme.hu.ibdb.network.repository.UserRepository
import szarch.bme.hu.ibdb.util.StringUtil
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val sharedPreferencesProvider: SharedPreferencesProvider,
    private val userRepository: UserRepository
) {

    suspend fun getFavourites(): List<Book> {
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

    suspend fun getUserInfo() {
        val userResponse = userRepository.getUserInfo()
        sharedPreferencesProvider.setUserRole(userResponse.role)
        sharedPreferencesProvider.setUserNickName(userResponse.nickname)
        sharedPreferencesProvider.setUserBirthDate(StringUtil.formatDateToString(userResponse.birthDate))
    }

    fun getUserAuthentication(): Boolean {
        return sharedPreferencesProvider.getClientAccessToken().isNotEmpty()
    }

    fun getUserIsAdmin(): Boolean {
        return sharedPreferencesProvider.getUserRole()
    }

    suspend fun updateUserInfo(nickname: String?, birthDate: String?) {
        return userRepository.updateUserInfo(birthDate = birthDate, nickname = nickname)
    }


}