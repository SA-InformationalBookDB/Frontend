package szarch.bme.hu.ibdb.ui.favourites

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
        GlobalScope.launch(Contexts.UI) {
            try {
                screen?.showFavouritesBooks(userInteractor.getFavourites())
            } catch (e: UnauthorizedException) {
                e.printStackTrace()
            } catch (e: ForbiddenException) {
                e.printStackTrace()
            } catch (e: NotFoundException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}