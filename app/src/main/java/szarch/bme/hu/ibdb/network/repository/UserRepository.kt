package szarch.bme.hu.ibdb.network.repository

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.network.api.UserApi
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.network.models.book.BookResponse
import szarch.bme.hu.ibdb.network.models.user.CategoriesUpdateRequest
import szarch.bme.hu.ibdb.network.models.user.UpdateUserRequest
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userApi: UserApi
) {

    suspend fun getFavourites(): List<BookResponse> = withContext(Contexts.NETWORK) {
        val response = userApi.getFavourites().execute()
        if (response.isSuccessful) {
            return@withContext response.body()!!
        } else {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

    suspend fun updateCategories(userId: String, categoriesUpdateRequest: CategoriesUpdateRequest) =
        withContext(Contexts.NETWORK) {

        }

    suspend fun addCategory(id: String, userId: String) = withContext(Contexts.NETWORK) {

    }

    suspend fun deleteCategory(id: String, userId: String) = withContext(Contexts.NETWORK) {

    }

    /*
    suspend fun getUserInfo():UserInfoResponse = withContext(Contexts.NETWORK){

    }*/

    suspend fun updateUserInfo(userId: String, updateUserRequest: UpdateUserRequest) = withContext(Contexts.NETWORK) {

    }

    suspend fun deleteUser(userId: String) = withContext(Contexts.NETWORK) {

    }

}
