package szarch.bme.hu.ibdb.ui.detail

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.network.repository.BookRepository
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val bookRepository: BookRepository
) : Presenter<DetailScreen>() {

    fun getBookDetails(bookId: String) {
        GlobalScope.launch(Contexts.UI) {
            screen?.showBookDetail(bookRepository.getBook(bookId))
        }
    }

}