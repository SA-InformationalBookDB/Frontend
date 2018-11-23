package szarch.bme.hu.ibdb.domain.interactors

import szarch.bme.hu.ibdb.network.models.book.BookResponse
import szarch.bme.hu.ibdb.network.repository.BookRepository
import javax.inject.Inject

class BookInteractor @Inject constructor(
    private val bookRepository: BookRepository
) {

    suspend fun getRecommendationBook(publishedAfter: String): List<BookResponse> {
        return bookRepository.getOfferBooks(publishedAfter)
    }

    suspend fun getBestsellerBooks(): List<BookResponse> {
        return bookRepository.getBestsellerBooks()
    }

    suspend fun getPublicBestsellerBooks(): List<BookResponse> {
        return bookRepository.getPublicBestsellerBooks()
    }

    suspend fun getPopularBooks(): List<BookResponse> {
        return bookRepository.getPopularBooks()
    }

    suspend fun getPublicPopularBooks(): List<BookResponse> {
        return bookRepository.getPublicPopularBooks()
    }

    suspend fun getTrendingBooks(publishedAfter: String): List<BookResponse> {
        return bookRepository.getTrendingBooks(publishedAfter)
    }

    suspend fun getPublicTrendingBooks(): List<BookResponse> {
        return bookRepository.getPublicTrendingBooks()
    }

    suspend fun getBook(bookId: String): BookResponse {
        return bookRepository.getBook(bookId)
    }

}