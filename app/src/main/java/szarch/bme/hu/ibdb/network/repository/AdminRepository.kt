package szarch.bme.hu.ibdb.network.repository

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.network.api.AdminApi
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdminRepository @Inject constructor(
    private val adminApi: AdminApi
) {

    suspend fun disableUser(userId: String) = withContext(Contexts.NETWORK) {

    }

    suspend fun enableUser(userId: String) = withContext(Contexts.NETWORK) {

    }

    suspend fun removeUser(userId: String) = withContext(Contexts.NETWORK) {

    }
}
