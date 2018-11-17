package szarch.bme.hu.ibdb.ui.favourites

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.OauthInteractor
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class FavouritePresenter @Inject constructor(
    private val oauthInteractor: OauthInteractor,
    private val userInteractor: UserInteractor
) : Presenter<FavouriteScreen>() {

    fun getFavouriteBooks() {
        GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> {
                    GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
                        screen?.showErrorMeaasge(throwable.message)
                    }) {
                        oauthInteractor.sendRefreshTokenRequest()
                        screen?.showFavouritesBooks(userInteractor.getFavourites())
                    }
                }
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
            Log.d("Testing", "getRecommendationBooks")
        }) {
            screen?.showFavouritesBooks(userInteractor.getFavourites())
        }
    }
}

