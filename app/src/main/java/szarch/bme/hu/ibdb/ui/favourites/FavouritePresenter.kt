package szarch.bme.hu.ibdb.ui.favourites

import szarch.bme.hu.ibdb.domain.models.Book
import szarch.bme.hu.ibdb.ui.base.Presenter

class FavouritePresenter : Presenter<FavouriteScreen>() {

    fun getFavouriteBooks() {
        screen?.showFavouritesBooks(getDemoBookList())
    }

    private fun getDemoBookList(): List<Book> {
        val list: MutableList<Book> = arrayListOf()
        for (i in 0..10) {
            list.add(
                i, Book(
                    id = i.toString(),
                    title = "Title",
                    author = "Author",
                    published = null,
                    categories = emptyList(),
                    imageUrl = "",
                    pageNumber = 100,
                    publisher = "",
                    reviews = emptyList(),
                    sold = 100,
                    summary = "",
                    views = 100
                )
            )
        }
        return list
    }

}