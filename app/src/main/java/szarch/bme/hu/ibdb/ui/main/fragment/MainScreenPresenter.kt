package szarch.bme.hu.ibdb.ui.main.fragment

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.network.repository.BookRepository
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class MainScreenPresenter @Inject constructor(
    private val bookRepository: BookRepository
) : Presenter<MainScreen>() {


    fun getRecommendationBooks() {
        GlobalScope.launch(Contexts.UI) {
            try {
                screen?.showRecommendationBooks(bookRepository.getOfferBooks())
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
                screen?.showBestsellerBooks(bookRepository.getBestsellerBooks())
            } catch (e: UnauthorizedException) {
                e.printStackTrace()
                screen?.showBestsellerBooks(bookRepository.getPublicPopularBooks())
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
                screen?.showPopularBooks(bookRepository.getPopularBooks())
            } catch (e: UnauthorizedException) {
                e.printStackTrace()
                screen?.showPopularBooks(bookRepository.getPublicPopularBooks())
            } catch (e: ForbiddenException) {
                e.printStackTrace()
            } catch (e: NotFoundException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTrendingBooks() {
        GlobalScope.launch(Contexts.UI) {
            try {
                screen?.showTrendingBooks(bookRepository.getTrendingBooks())
            } catch (e: UnauthorizedException) {
                e.printStackTrace()
                screen?.showTrendingBooks(bookRepository.getPublicTrendingBooks())
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