package szarch.bme.hu.ibdb.ui.activities

import szarch.bme.hu.ibdb.domain.models.Book
import szarch.bme.hu.ibdb.domain.models.Review
import szarch.bme.hu.ibdb.domain.models.User
import szarch.bme.hu.ibdb.ui.base.Presenter

class ActivitiesPresenter : Presenter<ActivitiesScreen>() {

    fun getActivities() {
        screen?.showActivities(getDemoBookList())
    }

    private fun getDemoBookList(): List<Review> {
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

        val reviewList: MutableList<Review> = arrayListOf()
        for (i in 0..10) {
            reviewList.add(
                i,
                Review(
                    id = i.toString(),
                    user = User(),
                    book = list[i],
                    points = 3.5,
                    comment = "This is the comment of this book"
                )
            )
        }
        return reviewList
    }

}