package szarch.bme.hu.ibdb.domain.interactors

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.network.models.book.BookRequest
import szarch.bme.hu.ibdb.network.models.user.UserInfo
import szarch.bme.hu.ibdb.network.repository.AdminRepository
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class AdminInteractor @Inject constructor(
    private val adminRepository: AdminRepository
) {

    suspend fun addBook(
        title: String, author: String, published: String?, publisher: String?,
        imageUrl: String?, summary: String, pageNumber: Int, sold: Int?
    ) {
        adminRepository.addBook(
            BookRequest(
                title,
                author,
                published,
                publisher,
                imageUrl,
                summary,
                pageNumber,
                sold
            )
        )
    }

    suspend fun updateBook(
        id: String, title: String, author: String, published: String?, publisher: String?,
        imageUrl: String?, summary: String, pageNumber: Int, sold: Int?, views: Int?
    ) = withContext(Contexts.UI) {
        adminRepository.updateBook(
            id,
            BookRequest(title, author, published, publisher, imageUrl, summary, pageNumber, sold)
        )
    }

    suspend fun deleteBook(id: String) {
        adminRepository.deleteBook(id)
    }

    suspend fun disableUser(userId: String) {
        adminRepository.disableUser(userId)
    }

    suspend fun enableUser(userId: String) {
        adminRepository.enableUser(userId)
    }

    suspend fun removeUser(userId: String) {
        adminRepository.removeUser(userId)
    }

    suspend fun removeReviewById(userId: String, reviewId: String) {
        adminRepository.removeReviewById(userId, reviewId)
    }

    suspend fun getUsers(): List<UserInfo> {
        return adminRepository.getUsers()
    }

}