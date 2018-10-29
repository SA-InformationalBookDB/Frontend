package szarch.bme.hu.ibdb.ui.search

import szarch.bme.hu.ibdb.domain.models.Book

interface SearchScreen {
    fun showBooks(bookList: List<Book>)
}