package szarch.bme.hu.ibdb.ui.search

import szarch.bme.hu.ibdb.network.models.book.BookResponse

interface SearchScreen {
    fun showBooks(bookList: List<BookResponse>)
}