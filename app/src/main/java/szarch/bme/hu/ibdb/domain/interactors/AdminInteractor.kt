package szarch.bme.hu.ibdb.domain.interactors

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.network.models.book.BookRequest
import szarch.bme.hu.ibdb.network.repository.AdminRepository
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class AdminInteractor @Inject constructor(
    private val adminRepository: AdminRepository
) {

    suspend fun addBook(
        title: String, author: String, published: String?, publisher: String?,
        imageUrl: String?, summary: String, pageNumber: Int, sold: Int?
    ) = withContext(Contexts.UI) {
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

    suspend fun deleteBook(id: String) = withContext(Contexts.UI) {
        adminRepository.deleteBook(id)
    }

    suspend fun disableUser(userId: String) = withContext(Contexts.UI) {
        adminRepository.disableUser(userId)
    }

    suspend fun enableUser(userId: String) = withContext(Contexts.UI) {
        adminRepository.enableUser(userId)
    }

    suspend fun removeUser(userId: String) = withContext(Contexts.UI) {
        adminRepository.removeUser(userId)
    }

    suspend fun removeReviewById(userId: String, reviewId: String) = withContext(Contexts.UI) {
        adminRepository.removeReviewById(userId, reviewId)
    }

    suspend fun getUsers() = withContext(Contexts.UI) {
        return@withContext adminRepository.getUsers()
    }

}