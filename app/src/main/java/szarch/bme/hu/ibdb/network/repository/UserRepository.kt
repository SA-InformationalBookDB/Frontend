package szarch.bme.hu.ibdb.network.repository

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.network.api.UserApi
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.network.models.book.BookResponse
import szarch.bme.hu.ibdb.network.models.user.CategoriesUpdateRequest
import szarch.bme.hu.ibdb.network.models.user.UpdateUserRequest
import szarch.bme.hu.ibdb.network.models.user.UserInfoResponse
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

    suspend fun updateCategories(categoriesUpdateRequest: CategoriesUpdateRequest) =
        withContext(Contexts.NETWORK) {
            val response = userApi.updateCategories(categoriesUpdateRequest).execute()
            if (response.isSuccessful.not()) {
                when (response.code()) {
                    401 -> throw UnauthorizedException("Unauthorized")
                    403 -> throw ForbiddenException("Forbidden")
                    404 -> throw NotFoundException("Not found")
                    else -> throw Exception(response.message())
                }
            }
        }

    suspend fun addFavourite(bookId: String) = withContext(Contexts.NETWORK) {
        val response = userApi.addFavourite(bookId).execute()
        if (response.isSuccessful.not()) {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

    suspend fun deleteFavourite(bookId: String) = withContext(Contexts.NETWORK) {
        val response = userApi.deleteFavourite(bookId).execute()
        if (response.isSuccessful.not()) {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

    suspend fun getUserInfo(): UserInfoResponse = withContext(Contexts.NETWORK) {
        val response = userApi.getUserInfo().execute()
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

    suspend fun updateUserInfo(updateUserRequest: UpdateUserRequest) = withContext(Contexts.NETWORK) {
        val response = userApi.updateUserInfo(updateUserRequest).execute()
        if (response.isSuccessful.not()) {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

    suspend fun deleteUser() = withContext(Contexts.NETWORK) {
        val response = userApi.deleteUser().execute()
        if (response.isSuccessful.not()) {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

}
