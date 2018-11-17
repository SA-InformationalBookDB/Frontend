package szarch.bme.hu.ibdb.ui.favourites

import szarch.bme.hu.ibdb.network.models.book.BookResponse

interface FavouriteScreen {

    fun showFavouritesBooks(bookList: List<BookResponse>)
    fun showErrorMeaasge(message: String?)
}