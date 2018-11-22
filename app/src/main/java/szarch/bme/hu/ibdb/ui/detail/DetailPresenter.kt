package szarch.bme.hu.ibdb.ui.detail

import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.BookInteractor
import szarch.bme.hu.ibdb.domain.interactors.ReviewInteractor
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
import szarch.bme.hu.ibdb.ui.base.Presenter
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val bookInteractor: BookInteractor,
    private val reviewInteractor: ReviewInteractor,
    private val userInteractor: UserInteractor
) : Presenter<DetailScreen>() {

    fun getBookDetails(bookId: String) {
        launch {
            try {
                screen?.showBookDetail(bookInteractor.getBook(bookId))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addFavourite(bookId: String) {
        launch {
            try {
                userInteractor.addFavourite(bookId)
                screen?.showSuccessfulFavouriteAdding()
            } catch (e: Exception) {
                screen?.showFavouriteError()
            }
        }
    }

    fun removeFavourite(bookId: String) {
        launch {
            try {
                userInteractor.removeFavourite(bookId)
                screen?.showSuccessfulFavouriteRemoval()
            } catch (e: Exception) {
                screen?.showFavouriteError()
            }
        }
    }

    fun getBookReviews(bookId: String) {
        launch {
            try {
                reviewInteractor.getReviews(bookId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

