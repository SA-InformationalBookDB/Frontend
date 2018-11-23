package szarch.bme.hu.ibdb.ui.main.fragment

import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.BookInteractor
import szarch.bme.hu.ibdb.domain.interactors.OauthInteractor
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import javax.inject.Inject

class MainScreenPresenter @Inject constructor(
    private val bookInteractor: BookInteractor,
    private val oauthInteractor: OauthInteractor,
    private val userInteractor: UserInteractor
) : Presenter<MainScreen>() {

    fun getRecommendationBooks(publishedAfter: String) {
        launch {
            try {
                screen?.showRecommendationBooks(bookInteractor.getRecommendationBook(publishedAfter))
            } catch (e: UnauthorizedException) {
                screen?.hideRecommendationBookList()
            } catch (e: ForbiddenException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getBestsellerBooks() {
        launch {
            try {
                screen?.showBestsellerBooks(bookInteractor.getBestsellerBooks())
            } catch (e: UnauthorizedException) {
                screen?.showErrorMessage(e.message)
                screen?.showBestsellerBooks(bookInteractor.getPublicBestsellerBooks())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun getPopularBooks() {
        launch {
            try {
                screen?.showPopularBooks(bookInteractor.getPopularBooks())
            } catch (e: UnauthorizedException) {
                screen?.showErrorMessage(e.message)
                screen?.showPopularBooks(bookInteractor.getPublicPopularBooks())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTrendingBooks(publishedAfter: String) {
        launch {
            try {
                screen?.showTrendingBooks(bookInteractor.getTrendingBooks(publishedAfter))
            } catch (e: UnauthorizedException) {
                screen?.showErrorMessage(e.message)
                screen?.showTrendingBooks(bookInteractor.getPublicTrendingBooks())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getUserIsAdmin() {
        launch {
            screen?.setUserInteractionPossibilities(userInteractor.getUserIsAdmin())
        }
    }

}