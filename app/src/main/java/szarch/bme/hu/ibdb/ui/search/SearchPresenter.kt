package szarch.bme.hu.ibdb.ui.search

import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.network.repository.BookRepository
import szarch.bme.hu.ibdb.ui.base.Presenter
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val bookRepository: BookRepository
) : Presenter<SearchScreen>() {

    fun searchBooks(query: String) {
        launch {
            try {
                screen?.showBooks(bookRepository.findBooks(query))
            } catch (e: UnauthorizedException) {
                try {
                    screen?.showBooks(bookRepository.findBooks(query))
                } catch (e: Exception) {
                    screen?.showErrorMessage(e.message)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}