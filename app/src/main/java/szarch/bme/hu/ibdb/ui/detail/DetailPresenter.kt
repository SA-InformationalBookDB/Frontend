package szarch.bme.hu.ibdb.ui.detail

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.network.repository.BookRepository
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val bookRepository: BookRepository
) : Presenter<DetailScreen>() {

    fun getBookDetails(bookId: String) {
        GlobalScope.launch(Contexts.UI + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
            Log.d("Testing", "getRecommendationBooks")
        }) {
            screen?.showBookDetail(bookRepository.getBook(bookId))
        }
    }
}

