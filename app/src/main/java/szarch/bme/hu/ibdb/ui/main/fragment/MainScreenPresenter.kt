package szarch.bme.hu.ibdb.ui.main.fragment

import szarch.bme.hu.ibdb.domain.models.Book
import szarch.bme.hu.ibdb.ui.base.Presenter

class MainScreenPresenter : Presenter<MainScreen>() {

    fun getFavouriteBooks() {
        screen?.showFavouriteBooks(getDemoBookList())
    }

    fun getRecommendationBooks() {
        screen?.showRecommendationBooks(getDemoBookList())
    }

    fun getBestsellerBooks() {
        screen?.showBestsellerBooks(getDemoBookList())
    }

    fun getPopularBooks() {
        screen?.showPopularBooks(getDemoBookList())
    }

    fun getTrendingBooks() {
        screen?.showTrendingBooks(getDemoBookList())
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