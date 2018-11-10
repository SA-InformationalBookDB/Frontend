package szarch.bme.hu.ibdb.network.repository

import kotlinx.coroutines.withContext
import szarch.bme.hu.ibdb.network.api.BookApi
import szarch.bme.hu.ibdb.network.models.book.BookRequest
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(
    private val bookApi: BookApi
) {

    /*
    suspend fun getPopularBooks()= withContext(Contexts.NETWORK){

    }

    suspend fun getBestsellerBooks()= withContext(Contexts.NETWORK){

    }

    suspend fun getOfferBooks()= withContext(Contexts.NETWORK){

    }

    suspend fun getTrendingBooks()= withContext(Contexts.NETWORK){

    }

    suspend fun getBook():BookResponse= withContext(Contexts.NETWORK){

    }

    suspend fun findBooks(page:Int, size:Int, queryString:String)= withContext(Contexts.NETWORK){

    }

    */

    suspend fun addBook(bookRequest: BookRequest) = withContext(Contexts.NETWORK) {

    }

    suspend fun updateBook(id: String, bookRequest: BookRequest) = withContext(Contexts.NETWORK) {

    }

    suspend fun deleteBook(id: String) = withContext(Contexts.NETWORK) {

    }

}