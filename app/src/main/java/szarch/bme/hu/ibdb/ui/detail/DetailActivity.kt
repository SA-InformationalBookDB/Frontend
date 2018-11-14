package szarch.bme.hu.ibdb.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.network.models.book.BookResponse
import szarch.bme.hu.ibdb.ui.base.BaseApplication
import szarch.bme.hu.ibdb.util.StringUtil
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailScreen {

    @Inject
    lateinit var detailPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectActivity()
        setContentView(R.layout.activity_detail)
        detailPresenter.attachScreen(this)
        detailPresenter.getBookDetails(intent.getStringExtra("bookId"))
    }

    override fun onStop() {
        detailPresenter.detachScreen()
        super.onStop()
    }

    private fun injectActivity() {
        (applicationContext as? BaseApplication)
            ?.injector
            ?.inject(this)
            ?: throw IllegalStateException("InjectedActivity should not be used without an Application that inherits from BaseApplication")
    }

    override fun showBookDetail(bookResponse: BookResponse) {

        Picasso.get()
            .load(bookResponse.imageUrl)
            .placeholder(R.drawable.ic_book_image_url)
            .resize(200, 200)
            .into(iv_detail)
        tv_detail_book_title.text = bookResponse.title
        tv_detail_book_author.text = bookResponse.author
        tv_detail_book_publisher.text = bookResponse.publisher
        bookResponse.published?.let {
            tv_detail_book_published.text = StringUtil.formatDateToString(it)
        }
        tv_detail_book_summary.text = bookResponse.summary
        tv_detail_book_page_number.text = bookResponse.pageNumber.toString()
        bookResponse.sold?.let {
            tv_detail_book_sold.text = it.toString()
        }
        bookResponse.views?.let {
            tv_detail_book_views.text = it.toString()
        }
    }

    override fun showBookError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
