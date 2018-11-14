package szarch.bme.hu.ibdb.ui.main.fragment

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
        GlobalScope.launch(Contexts.UI) {
            try {
                screen?.showRecommendationBooks(bookInteractor.getRecommendationBook())
            } catch (e: UnauthorizedException) {
                e.printStackTrace()
            } catch (e: ForbiddenException) {
                e.printStackTrace()
            } catch (e: NotFoundException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getBestsellerBooks() {
        GlobalScope.launch(Contexts.UI) {
            try {
                screen?.showBestsellerBooks(bookInteractor.getBestsellerBooks())
            } catch (e: UnauthorizedException) {
                e.printStackTrace()
                screen?.showBestsellerBooks(bookInteractor.getPublicBestsellerBooks())
            } catch (e: ForbiddenException) {
                e.printStackTrace()
            } catch (e: NotFoundException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getPopularBooks() {
        GlobalScope.launch(Contexts.UI) {
            try {
                screen?.showPopularBooks(bookInteractor.getPopularBooks())
            } catch (e: UnauthorizedException) {
                e.printStackTrace()
                screen?.showPopularBooks(bookInteractor.getPublicPopularBooks())
            } catch (e: ForbiddenException) {
                e.printStackTrace()
            } catch (e: NotFoundException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTrendingBooks(publishedAfter: String) {
        GlobalScope.launch(Contexts.UI) {
            try {
                screen?.showTrendingBooks(bookInteractor.getTrendingBooks(publishedAfter))
            } catch (e: UnauthorizedException) {
                e.printStackTrace()
                screen?.showTrendingBooks(bookInteractor.getPublicTrendingBooks())
            } catch (e: ForbiddenException) {
                e.printStackTrace()
            } catch (e: NotFoundException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}