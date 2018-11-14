package szarch.bme.hu.ibdb.ui.main.fragment

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.BookInteractor
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class MainScreenPresenter @Inject constructor(
    private val bookInteractor: BookInteractor
) : Presenter<MainScreen>() {


    fun getRecommendationBooks() {
        GlobalScope.launch(Contexts.UI + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
            Log.d("Testing", "getRecommendationBooks")
        }) {
            screen?.showRecommendationBooks(bookInteractor.getRecommendationBook())
        }
    }

    fun getBestsellerBooks() {
        GlobalScope.launch(Contexts.UI + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
            Log.d("Testing", "getBestsellerBooks")
        }) {
            screen?.showBestsellerBooks(bookInteractor.getBestsellerBooks())
        }
    }

    fun getPopularBooks() {
        GlobalScope.launch(Contexts.UI + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
            Log.d("Testing", "getPopularBooks")
        }) {
            screen?.showPopularBooks(bookInteractor.getPopularBooks())
        }
    }

    fun getTrendingBooks(publishedAfter: String) {
        GlobalScope.launch(Contexts.UI + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
            Log.d("Testing", "getTrendingBooks")
        }) {
            screen?.showTrendingBooks(bookInteractor.getTrendingBooks(publishedAfter))
        }
    }


}