package szarch.bme.hu.ibdb.ui.favourites

import szarch.bme.hu.ibdb.domain.models.Book

interface FavouriteScreen {

    fun showFavouritesBooks(bookList: List<Book>)
}