package szarch.bme.hu.ibdb.ui.activities

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.OauthInteractor
import szarch.bme.hu.ibdb.domain.interactors.ReviewInteractor
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class ActivitiesPresenter @Inject constructor(
    private val reviewInteractor: ReviewInteractor,
    private val oauthInteractor: OauthInteractor
) : Presenter<ActivitiesScreen>() {

    fun getActivities() {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> {
                    GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
                        when (throwable) {
                            is UnauthorizedException -> throwable.printStackTrace()
                            is ForbiddenException -> throwable.printStackTrace()
                            is NotFoundException -> throwable.printStackTrace()
                            else -> throwable.printStackTrace()
                        }
                    }) {
                        oauthInteractor.sendRefreshTokenRequest()
                        screen?.showActivities(reviewInteractor.getUserReviews())
                    }
                }
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
        }) {
            screen?.showActivities(reviewInteractor.getUserReviews())
        }
    }

}