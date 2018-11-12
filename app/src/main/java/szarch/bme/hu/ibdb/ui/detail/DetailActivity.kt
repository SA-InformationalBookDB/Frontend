package szarch.bme.hu.ibdb.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_detail.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.network.models.book.BookResponse
import szarch.bme.hu.ibdb.ui.base.BaseApplication

class DetailActivity : AppCompatActivity(), DetailScreen {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectActivity()
        setContentView(R.layout.activity_detail)
    }

    private fun injectActivity() {
        (applicationContext as? BaseApplication)
            ?.injector
            ?.inject(this)
            ?: throw IllegalStateException("InjectedActivity should not be used without an Application that inherits from BaseApplication")
    }

    override fun showBookDetail(bookResponse: BookResponse) {
        bookResponse.imageUrl?.let {
            /*GlideApp.with(this)
                .load(it)
                .into(iv_detail_book_image_url)*/

        }

        tv_detail_book_title.text = bookResponse.title

    }

}
