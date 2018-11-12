package szarch.bme.hu.ibdb.ui.main.fragment

import szarch.bme.hu.ibdb.network.models.book.BookResponse

interface MainScreen {
    fun showRecommendationBooks(bookList: List<BookResponse>)
    fun showBestsellerBooks(bookList: List<BookResponse>)
    fun showPopularBooks(bookList: List<BookResponse>)
    fun showTrendingBooks(bookList: List<BookResponse>)
}