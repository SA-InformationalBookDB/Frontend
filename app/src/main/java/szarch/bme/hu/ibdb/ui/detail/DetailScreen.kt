package szarch.bme.hu.ibdb.ui.detail

import szarch.bme.hu.ibdb.network.models.book.BookResponse

interface DetailScreen {
    fun showBookDetail(bookResponse: BookResponse)
    fun showBookError(message: String)
    fun showSuccessfulFavouriteAdding()
    fun showSuccessfulFavouriteRemoval()
    fun showFavouriteError()
}
