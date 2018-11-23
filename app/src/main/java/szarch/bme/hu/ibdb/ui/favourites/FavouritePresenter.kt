package szarch.bme.hu.ibdb.ui.favourites

import android.content.res.Resources
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.interactors.OauthInteractor
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import javax.inject.Inject

class FavouritePresenter @Inject constructor(
    private val oauthInteractor: OauthInteractor,
    private val userInteractor: UserInteractor,
    private val resources: Resources
) : Presenter<FavouriteScreen>() {

    fun getFavouriteBooks() {
        launch {
            try {
                screen?.showFavouritesBooks(userInteractor.getFavourites())
            } catch (e: UnauthorizedException) {
                try {
                    oauthInteractor.sendRefreshTokenRequest()
                    screen?.showFavouritesBooks(userInteractor.getFavourites())
                } catch (e: Exception) {
                    screen?.showErrorMeaasge(resources.getString(R.string.error_text_possible_authorization_problems))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

