package szarch.bme.hu.ibdb.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.network.models.book.Book
import szarch.bme.hu.ibdb.ui.base.BaseApplication
import szarch.bme.hu.ibdb.util.Navigator
import szarch.bme.hu.ibdb.util.StringUtil
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailScreen {

    @Inject
    lateinit var detailPresenter: DetailPresenter

    private lateinit var book: Book
    private var isFavourite: Boolean = false
    private var menuItem: MenuItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectActivity()
        setContentView(R.layout.activity_detail)
        detailPresenter.attachScreen(this)
        if (intent.getStringExtra(INTENT_BOOK_ID) != null) {
            detailPresenter.getBookDetails(intent.getStringExtra(INTENT_BOOK_ID))
        }
    }

    override fun onStop() {
        detailPresenter.detachScreen()
        super.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu?.findItem(R.id.detail_favourite)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {

            R.id.detail_favourite -> {
                if (isFavourite) {
                    detailPresenter.removeFavourite(book.id)
                } else {
                    detailPresenter.addFavourite(book.id)
                }

                true
            }

            R.id.detail_reviews -> {
                Navigator.navigateToReviewActivity(this@DetailActivity, book.id)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun injectActivity() {
        (applicationContext as? BaseApplication)
            ?.injector
            ?.inject(this)
            ?: throw IllegalStateException("InjectedActivity should not be used without an Application that inherits from BaseApplication")
    }

    override fun showBookDetail(book: Book) {
        this.book = book
        Picasso.get()
            .load(book.imageUrl)
            .placeholder(R.drawable.ic_book_image_url)
            .resize(200, 200)
            .into(iv_detail)
        book.averageRating?.let {
            rt_detail_book_title.rating = (it % 5.0).toFloat()
        }
        tv_detail_book_title.text = book.title
        tv_detail_book_author.text = book.author
        tv_detail_book_publisher.text = book.publisher
        book.published?.let {
            tv_detail_book_published.text = StringUtil.formatDateToString(it)
        }
        tv_detail_book_summary.text = book.summary
        tv_detail_book_page_number.text = book.pageNumber.toString()
        book.sold?.let {
            tv_detail_book_sold.text = it.toString()
        }
        book.views?.let {
            tv_detail_book_views.text = it.toString()
        }
        book.favourite?.let {
            menuItem?.icon =
                    if (it) ContextCompat.getDrawable(this, R.drawable.ic_favourites) else ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_favourites_off
                    )
            isFavourite = it
        }
    }

    override fun showSuccessfulFavouriteAdding() {
        menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favourites)
        isFavourite = true
        Toast.makeText(this, resources.getString(R.string.successful_favourite_adding), Toast.LENGTH_LONG).show()
    }

    override fun showSuccessfulFavouriteRemoval() {
        menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favourites_off)
        isFavourite = false
        Toast.makeText(this, resources.getString(R.string.successful_favourite_removing), Toast.LENGTH_LONG).show()
    }

    override fun showFavouriteError() {
        Toast.makeText(this, resources.getString(R.string.unsuccessful_favourite_text), Toast.LENGTH_LONG).show()
    }

    override fun showBookError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        const val INTENT_BOOK_ID = "bookID"
    }

}
