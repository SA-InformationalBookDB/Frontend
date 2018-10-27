package szarch.bme.hu.ibdb.ui.main.fragment

import szarch.bme.hu.ibdb.domain.models.Book

interface MainScreen {
    fun showFavouriteBooks(bookList: List<Book>)
    fun showRecommendationBooks(bookList: List<Book>)
    fun showBestsellerBooks(bookList: List<Book>)
    fun showPopularBooks(bookList: List<Book>)
    fun showTrendingBooks(bookList: List<Book>)
}