package szarch.bme.hu.ibdb.domain.interactors

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.network.models.book.BookResponse
import szarch.bme.hu.ibdb.network.repository.BookRepository
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class BookInteractor @Inject constructor(
    private val bookRepository: BookRepository
) {

    suspend fun getRecommendationBook(): List<BookResponse> = withContext(Contexts.UI) {
        return@withContext bookRepository.getOfferBooks()
    }

    suspend fun getBestsellerBooks(): List<BookResponse> = withContext(Contexts.UI) {
        return@withContext bookRepository.getBestsellerBooks()
    }

    suspend fun getPublicBestsellerBooks(): List<BookResponse> = withContext(Contexts.UI) {
        return@withContext bookRepository.getPublicBestsellerBooks()
    }

    suspend fun getPopularBooks(): List<BookResponse> = withContext(Contexts.UI) {
        return@withContext bookRepository.getPopularBooks()
    }

    suspend fun getPublicPopularBooks(): List<BookResponse> = withContext(Contexts.UI) {
        return@withContext bookRepository.getPublicPopularBooks()
    }

    suspend fun getTrendingBooks(publishedAfter: String): List<BookResponse> = withContext(Contexts.UI) {
        return@withContext bookRepository.getTrendingBooks(publishedAfter)
    }

    suspend fun getPublicTrendingBooks(): List<BookResponse> = withContext(Contexts.UI) {
        return@withContext bookRepository.getPublicTrendingBooks()
    }

}