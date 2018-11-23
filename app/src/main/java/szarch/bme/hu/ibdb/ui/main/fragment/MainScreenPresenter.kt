package szarch.bme.hu.ibdb.ui.main.fragment

import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.BookInteractor
import szarch.bme.hu.ibdb.domain.interactors.OauthInteractor
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
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
                try {
                    oauthInteractor.sendRefreshTokenRequest()
                    screen?.showRecommendationBooks(bookInteractor.getRecommendationBook(publishedAfter))
                } catch (e: Exception) {
                    screen?.hideRecommendationBookList()
                }
            } catch (e: Exception) {
                screen?.hideRecommendationBookList()
            }
        }
    }

    fun getBestsellerBooks() {
        launch {
            try {
                screen?.showBestsellerBooks(bookInteractor.getBestsellerBooks())
            } catch (e: UnauthorizedException) {
                try {
                    oauthInteractor.sendRefreshTokenRequest()
                    screen?.showBestsellerBooks(bookInteractor.getBestsellerBooks())
                } catch (e: Exception) {
                    try {
                        screen?.showBestsellerBooks(bookInteractor.getPublicBestsellerBooks())
                    } catch (e: Exception) {
                        screen?.showErrorMessage(e.message)
                    }
                }
            } catch (e: Exception) {
                screen?.showErrorMessage(e.message)
            }
        }

    }

    fun getPopularBooks() {
        launch {
            try {
                screen?.showPopularBooks(bookInteractor.getPopularBooks())
            } catch (e: UnauthorizedException) {
                try {
                    oauthInteractor.sendRefreshTokenRequest()
                    screen?.showPopularBooks(bookInteractor.getPopularBooks())
                } catch (e: Exception) {
                    try {
                        screen?.showPopularBooks(bookInteractor.getPublicPopularBooks())
                    } catch (e: Exception) {
                        screen?.showErrorMessage(e.message)
                    }
                }
            } catch (e: Exception) {
                screen?.showErrorMessage(e.message)
            }
        }
    }

    fun getTrendingBooks(publishedAfter: String) {
        launch {
            try {
                screen?.showTrendingBooks(bookInteractor.getTrendingBooks(publishedAfter))
            } catch (e: UnauthorizedException) {
                try {
                    oauthInteractor.sendRefreshTokenRequest()
                    screen?.showTrendingBooks(bookInteractor.getTrendingBooks(publishedAfter))
                } catch (e: Exception) {
                    try {
                        screen?.showTrendingBooks(bookInteractor.getPublicTrendingBooks())
                    } catch (e: Exception) {
                        screen?.showErrorMessage(e.message)
                    }
                }
            } catch (e: Exception) {
                screen?.showErrorMessage(e.message)
            }
        }
    }

    fun getUserIsAdmin() {
        launch {
            screen?.setUserInteractionPossibilities(userInteractor.getUserIsAdmin())
        }
    }

}