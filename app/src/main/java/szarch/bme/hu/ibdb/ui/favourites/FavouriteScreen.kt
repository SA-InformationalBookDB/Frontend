package szarch.bme.hu.ibdb.ui.favourites

import szarch.bme.hu.ibdb.network.models.book.Book

interface FavouriteScreen {

    fun showFavouritesBooks(bookList: List<Book>)
    fun showErrorMeaasge(message: String?)
}