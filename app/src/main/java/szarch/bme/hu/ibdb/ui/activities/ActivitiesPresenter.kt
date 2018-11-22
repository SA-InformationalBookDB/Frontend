package szarch.bme.hu.ibdb.ui.activities

import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.OauthInteractor
import szarch.bme.hu.ibdb.domain.interactors.ReviewInteractor
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import javax.inject.Inject

class ActivitiesPresenter @Inject constructor(
    private val reviewInteractor: ReviewInteractor,
    private val oauthInteractor: OauthInteractor
) : Presenter<ActivitiesScreen>() {

    fun getActivities() {
        launch {
            try {
                screen?.showActivities(reviewInteractor.getUserReviews())
            } catch (e: UnauthorizedException) {
                try {
                    oauthInteractor.sendRefreshTokenRequest()
                    screen?.showActivities(reviewInteractor.getUserReviews())
                } catch (e: Exception) {
                    screen?.showErrorMessage(e.message)
                }
            }
        }

    }

}