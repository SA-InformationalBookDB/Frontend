package szarch.bme.hu.ibdb.ui.main.fragment

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.network.repository.BookRepository
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class MainScreenPresenter @Inject constructor(
    private val bookRepository: BookRepository
) : Presenter<MainScreen>() {


    fun getRecommendationBooks() {
        GlobalScope.launch(Contexts.UI) {
            screen?.showRecommendationBooks(bookRepository.getOfferBooks())
        }
    }

    fun getBestsellerBooks() {
        GlobalScope.launch(Contexts.UI) {
            screen?.showBestsellerBooks(bookRepository.getBestsellerBooks())
        }
    }

    fun getPopularBooks() {
        GlobalScope.launch(Contexts.UI) {
            screen?.showPopularBooks(bookRepository.getPopularBooks())
        }
    }

    fun getTrendingBooks() {
        GlobalScope.launch(Contexts.UI) {
            screen?.showTrendingBooks(bookRepository.getTrendingBooks())
        }
    }


}