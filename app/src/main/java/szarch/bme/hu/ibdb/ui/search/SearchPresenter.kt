package szarch.bme.hu.ibdb.ui.search

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import szarch.bme.hu.ibdb.network.exception.ForbiddenException
import szarch.bme.hu.ibdb.network.exception.NotFoundException
import szarch.bme.hu.ibdb.network.exception.UnauthorizedException
import szarch.bme.hu.ibdb.network.repository.BookRepository
import szarch.bme.hu.ibdb.ui.base.Presenter
import szarch.bme.hu.ibdb.util.Contexts
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val bookRepository: BookRepository
) : Presenter<SearchScreen>() {

    fun searchBooks(query: String) {
        GlobalScope.launch(Contexts.UI + CoroutineExceptionHandler { coroutineContext, throwable ->
            when (throwable) {
                is UnauthorizedException -> {
                    GlobalScope.launch(Contexts.UI + job + CoroutineExceptionHandler { coroutineContext, throwable ->
                        screen?.showErrorMessage(throwable.message)
                    }) {
                        screen?.showBooks(bookRepository.findBooks(query))
                    }
                }
                is ForbiddenException -> throwable.printStackTrace()
                is NotFoundException -> throwable.printStackTrace()
                else -> throwable.printStackTrace()
            }
        }) {
            screen?.showBooks(bookRepository.findBooks(query))
        }
    }

}