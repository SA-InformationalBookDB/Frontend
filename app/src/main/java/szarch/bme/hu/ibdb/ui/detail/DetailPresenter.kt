package szarch.bme.hu.ibdb.ui.detail

import android.content.res.Resources
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.interactors.BookInteractor
import szarch.bme.hu.ibdb.domain.interactors.OauthInteractor
import szarch.bme.hu.ibdb.domain.interactors.UserInteractor
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.ui.base.Presenter
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val bookInteractor: BookInteractor,
    private val oauthInteractor: OauthInteractor,
    private val userInteractor: UserInteractor,
    private val resources: Resources
) : Presenter<DetailScreen>() {

    fun getBookDetails(bookId: String) {
        launch {
            try {
                screen?.showBookDetail(bookInteractor.getBook(bookId))
            } catch (e: UnauthorizedException) {
                try {
                    oauthInteractor.sendRefreshTokenRequest()
                    screen?.showBookDetail(bookInteractor.getBook(bookId))
                } catch (e: Exception) {
                    screen?.showBookError(resources.getString(R.string.error_text))
                }
            } catch (e: Exception) {
                screen?.showBookError(resources.getString(R.string.error_text))
            }
        }
    }

    fun addFavourite(bookId: String) {
        launch {
            try {
                userInteractor.addFavourite(bookId)
                screen?.showSuccessfulFavouriteAdding()
            } catch (e: UnauthorizedException) {
                try {
                    oauthInteractor.sendRefreshTokenRequest()
                    userInteractor.addFavourite(bookId)
                    screen?.showSuccessfulFavouriteAdding()
                } catch (e: Exception) {
                    screen?.showFavouriteError()
                }
            } catch (e: Exception) {
                screen?.showFavouriteError()
            }
        }
    }

    fun removeFavourite(bookId: String) {
        launch {
            try {
                userInteractor.removeFavourite(bookId)
                screen?.showSuccessfulFavouriteRemoval()
            } catch (e: UnauthorizedException) {
                try {
                    oauthInteractor.sendRefreshTokenRequest()
                    userInteractor.removeFavourite(bookId)
                    screen?.showSuccessfulFavouriteRemoval()
                } catch (e: Exception) {
                    screen?.showFavouriteError()
                }
            } catch (e: Exception) {
                screen?.showFavouriteError()
            }
        }
    }

}

