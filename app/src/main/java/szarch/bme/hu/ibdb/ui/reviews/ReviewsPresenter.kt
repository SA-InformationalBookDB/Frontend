package szarch.bme.hu.ibdb.ui.reviews

import android.content.res.Resources
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.interactors.OauthInteractor
import szarch.bme.hu.ibdb.domain.interactors.ReviewInteractor
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import javax.inject.Inject

class ReviewsPresenter @Inject constructor(
    private val reviewInteractor: ReviewInteractor,
    private val oauthInteractor: OauthInteractor,
    private val resources: Resources
) : Presenter<ReviewsScreen>() {

    fun getReviews(bookId: String) {
        launch {
            try {
                screen?.showReviews(reviewInteractor.getReviews(bookId))
            } catch (e: UnauthorizedException) {
                try {
                    oauthInteractor.sendRefreshTokenRequest()
                    screen?.showReviews(reviewInteractor.getReviews(bookId))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun sendReview(bookId: String, points: Double, comment: String?) {
        launch {
            try {
                reviewInteractor.sendReview(bookId, points, comment)
                screen?.showActionResult(resources.getString(R.string.successful_review))
            } catch (e: UnauthorizedException) {
                try {
                    oauthInteractor.sendRefreshTokenRequest()
                    reviewInteractor.sendReview(bookId, points, comment)
                    screen?.showActionResult(resources.getString(R.string.successful_review))
                } catch (e: Exception) {
                    screen?.showActionResult(resources.getString(R.string.unsuccessful_review))
                }
            } catch (e: Exception) {
                screen?.showActionResult(resources.getString(R.string.unsuccessful_review))
            }
        }
    }

    fun removeReview(reviewId: String) {
        launch {
            try {
                reviewInteractor.removeReview(reviewId)
                screen?.showActionResult(resources.getString(R.string.successful_review_removal))
            } catch (e: UnauthorizedException) {
                try {
                    oauthInteractor.sendRefreshTokenRequest()
                    reviewInteractor.removeReview(reviewId)
                    screen?.showActionResult(resources.getString(R.string.successful_review_removal))
                } catch (e: Exception) {
                    screen?.showActionResult(resources.getString(R.string.unsuccessful_review_removal))
                }
            } catch (e: Exception) {
                screen?.showActionResult(resources.getString(R.string.unsuccessful_review_removal))
            }
        }
    }

}