package szarch.bme.hu.ibdb.ui.reviews

import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.ReviewInteractor
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import javax.inject.Inject

class ReviewsPresenter @Inject constructor(
    private val reviewInteractor: ReviewInteractor
) : Presenter<ReviewsScreen>() {

    fun getReviews(bookId: String) {
        launch {
            try {
                screen?.showReviews(reviewInteractor.getReviews(bookId))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun sendReview(bookId: String, points: Double, comment: String?) {
        launch {
            try {
                reviewInteractor.sendReview(bookId, points, comment)
                screen?.showSuccessfulReviewSending()
            } catch (e: UnauthorizedException) {
                e.printStackTrace()
            } catch (e: Exception) {
                screen?.showUnsuccessfulReviewSending()
            }
        }
    }

}