package szarch.bme.hu.ibdb.ui.favourites

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.network.repository.UserRepository
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class FavouritePresenter @Inject constructor(
    private val userRepository: UserRepository
) : Presenter<FavouriteScreen>() {

    fun getFavouriteBooks() {
        GlobalScope.launch(Contexts.UI) {
            screen?.showFavouritesBooks(userRepository.getFavourites())
        }
    }

}