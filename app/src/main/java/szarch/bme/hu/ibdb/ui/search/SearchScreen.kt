package szarch.bme.hu.ibdb.ui.search

import szarch.bme.hu.ibdb.network.models.book.Book

interface SearchScreen {
    fun showBooks(bookList: List<Book>)
    fun showErrorMessage(message: String?)
}