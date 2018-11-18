package szarch.bme.hu.ibdb.ui.reviews

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.ReviewInteractor
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class ReviewsPresenter @Inject constructor(
    private val reviewInteractor: ReviewInteractor
) : Presenter<ReviewsScreen>() {

    fun getReviews(bookId: String) {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
        }) {
            screen?.showReviews(reviewInteractor.getReviews(bookId))
        }
    }

    fun sendReview(bookId: String, points: Double, comment: String?) {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
                is ForbiddenException -> screen?.showUnsuccessfulReviewSending()
                is NotFoundException -> screen?.showUnsuccessfulReviewSending()
                else -> screen?.showUnsuccessfulReviewSending()
            }
        }) {
            reviewInteractor.sendReview(bookId, points, comment)
            screen?.showSuccessfulReviewSending()
        }
    }

}