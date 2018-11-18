package szarch.bme.hu.ibdb.network.repository

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.network.api.AdminApi
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.network.models.book.BookRequest
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdminRepository @Inject constructor(
    private val adminApi: AdminApi
) {

    suspend fun addBook(bookRequest: BookRequest) = withContext(Contexts.NETWORK) {
        val response = adminApi.addBook(bookRequest).execute()
        if (response.isSuccessful.not()) {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

    suspend fun updateBook(id: String, bookRequest: BookRequest) = withContext(Contexts.NETWORK) {
        val response = adminApi.updateBook(id, bookRequest).execute()
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

    suspend fun deleteBook(id: String) = withContext(Contexts.NETWORK) {
        val response = adminApi.deleteBook(id).execute()
        if (response.isSuccessful.not())
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
    }


    suspend fun disableUser(userId: String) = withContext(Contexts.NETWORK) {
        val response = adminApi.disableUser(userId).execute()
        if (response.isSuccessful.not()) {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

    suspend fun enableUser(userId: String) = withContext(Contexts.NETWORK) {
        val response = adminApi.enableUser(userId).execute()
        if (response.isSuccessful.not()) {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

    suspend fun removeUser(userId: String) = withContext(Contexts.NETWORK) {
        val response = adminApi.removeUser(userId).execute()
        if (response.isSuccessful.not()) {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

    suspend fun removeReviewById(userId: String, reviewId: String) = withContext(Contexts.NETWORK) {
        val response = adminApi.deleteReviewByAdmin(userId, reviewId).execute()
        if (response.isSuccessful.not()) {
            when (response.code()) {
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not found")
                else -> throw Exception(response.message())
            }
        }
    }

    suspend fun getUsers() = withContext(Contexts.NETWORK) {
        val response = adminApi.getUsers().execute()
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
}
