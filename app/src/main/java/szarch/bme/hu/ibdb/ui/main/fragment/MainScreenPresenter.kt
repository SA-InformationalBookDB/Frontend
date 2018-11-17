package szarch.bme.hu.ibdb.ui.main.fragment

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.BookInteractor
import szarch.bme.hu.ibdb.domain.interactors.OauthInteractor
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class MainScreenPresenter @Inject constructor(
    private val bookInteractor: BookInteractor,
    private val oauthInteractor: OauthInteractor,
    private val userInteractor: UserInteractor
) : Presenter<MainScreen>() {

    fun getRecommendationBooks() {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> {
                    screen?.hideRecommendationBookList()
                }
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
        }) {
            screen?.showRecommendationBooks(bookInteractor.getRecommendationBook())
        }
    }

    fun getBestsellerBooks() {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> {
                    GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
                        screen?.showErrorMessage(throwable.message)
                    }) {
                        screen?.showBestsellerBooks(bookInteractor.getPublicBestsellerBooks())
                    }
                }
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
        }) {
            screen?.showBestsellerBooks(bookInteractor.getBestsellerBooks())
        }
    }

    fun getPopularBooks() {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> {
                    GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
                        when (throwable) {
                            is UnauthorizedException -> throwable.printStackTrace()
                            is ForbiddenException -> throwable.printStackTrace()
                            is NotFoundException -> throwable.printStackTrace()
                            else -> throwable.printStackTrace()
                        }
                    }) {
                        screen?.showPopularBooks(bookInteractor.getPublicPopularBooks())
                    }
                }
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
        }) {
            screen?.showPopularBooks(bookInteractor.getPopularBooks())
        }
    }

    fun getTrendingBooks(publishedAfter: String) {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> {
                    GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
                        when (throwable) {
                            is UnauthorizedException -> throwable.printStackTrace()
                            is ForbiddenException -> throwable.printStackTrace()
                            is NotFoundException -> throwable.printStackTrace()
                            else -> throwable.printStackTrace()
                        }
                    }) {
                        screen?.showTrendingBooks(bookInteractor.getPublicTrendingBooks())
                    }
                }
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
        }) {
            screen?.showTrendingBooks(bookInteractor.getTrendingBooks(publishedAfter))
        }
    }

    fun getUserIsAdmin() {
        GlobalScope.launch(Contexts.UI) {
            screen?.setUserInteractionPossibilities(userInteractor.getUserIsAdmin())
        }
    }

}