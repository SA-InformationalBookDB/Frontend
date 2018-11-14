package szarch.bme.hu.ibdb.ui.favourites

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class FavouritePresenter @Inject constructor(
    private val userInteractor: UserInteractor
) : Presenter<FavouriteScreen>() {

    fun getFavouriteBooks() {
        GlobalScope.launch(Contexts.UI + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> throwable.printStackTrace()
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

