package szarch.bme.hu.ibdb.ui.detail

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.BookInteractor
import szarch.bme.hu.ibdb.domain.interactors.ReviewInteractor
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val bookInteractor: BookInteractor,
    private val reviewInteractor: ReviewInteractor,
    private val userInteractor: UserInteractor
) : Presenter<DetailScreen>() {

    fun getBookDetails(bookId: String) {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
        }) {
            screen?.showBookDetail(bookInteractor.getBook(bookId))
        }
    }

    fun addFavourite(bookId: String) {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
            screen?.showFavouriteError()
        }) {
            userInteractor.addFavourite(bookId)
            screen?.showSuccessfulFavouriteAdding()
        }
    }

    fun removeFavourite(bookId: String) {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
            screen?.showFavouriteError()
        }) {
            userInteractor.removeFavourite(bookId)
            screen?.showSuccessfulFavouriteRemoval()
        }
    }

    fun getBookReviews(bookId: String) {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
            Log.d("Testing", "getRecommendationBooks")
        }) {
            reviewInteractor.getReviews(bookId)
        }
    }
}

