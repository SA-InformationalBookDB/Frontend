package szarch.bme.hu.ibdb.ui.main.fragment

import szarch.bme.hu.ibdb.network.models.book.Book

interface MainScreen {
    fun showRecommendationBooks(bookList: List<Book>)
    fun showBestsellerBooks(bookList: List<Book>)
    fun showPopularBooks(bookList: List<Book>)
    fun showTrendingBooks(bookList: List<Book>)
    fun setUserInteractionPossibilities(userIsAdmin: Boolean)
    fun showErrorMessage(message: String?)
    fun hideRecommendationBookList()
}