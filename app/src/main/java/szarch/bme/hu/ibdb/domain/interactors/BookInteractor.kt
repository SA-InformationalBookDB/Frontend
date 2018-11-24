package szarch.bme.hu.ibdb.domain.interactors

import szarch.bme.hu.ibdb.network.models.book.Book
import szarch.bme.hu.ibdb.network.repository.BookRepository
import javax.inject.Inject

class BookInteractor @Inject constructor(
    private val bookRepository: BookRepository
) {

    suspend fun getRecommendationBook(publishedAfter: String): List<Book> {
        return bookRepository.getOfferBooks(publishedAfter)
    }

    suspend fun getBestsellerBooks(): List<Book> {
        return bookRepository.getBestsellerBooks()
    }

    suspend fun getPublicBestsellerBooks(): List<Book> {
        return bookRepository.getPublicBestsellerBooks()
    }

    suspend fun getPopularBooks(): List<Book> {
        return bookRepository.getPopularBooks()
    }

    suspend fun getPublicPopularBooks(): List<Book> {
        return bookRepository.getPublicPopularBooks()
    }

    suspend fun getTrendingBooks(publishedAfter: String): List<Book> {
        return bookRepository.getTrendingBooks(publishedAfter)
    }

    suspend fun getPublicTrendingBooks(): List<Book> {
        return bookRepository.getPublicTrendingBooks()
    }

    suspend fun getBook(bookId: String): Book {
        return bookRepository.getBook(bookId)
    }

}