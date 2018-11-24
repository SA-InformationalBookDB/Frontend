package szarch.bme.hu.ibdb.ui.detail

import szarch.bme.hu.ibdb.network.models.book.Book

interface DetailScreen {
    fun showBookDetail(book: Book)
    fun showBookError(message: String)
    fun showSuccessfulFavouriteAdding()
    fun showSuccessfulFavouriteRemoval()
    fun showFavouriteError()
}
