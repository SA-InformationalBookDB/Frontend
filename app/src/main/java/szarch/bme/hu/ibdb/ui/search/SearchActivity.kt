package szarch.bme.hu.ibdb.ui.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.network.models.book.BookResponse
import szarch.bme.hu.ibdb.ui.base.BaseApplication
import szarch.bme.hu.ibdb.ui.favourites.FavouriteAdapter
import javax.inject.Inject


class SearchActivity : AppCompatActivity(), SearchScreen {

    @Inject
    lateinit var searchPresenter: SearchPresenter

    private lateinit var favouriteAdapter: FavouriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectActivity()
        setContentView(R.layout.activity_search)
    }

    override fun onStart() {
        super.onStart()
        searchPresenter.attachScreen(this)
    }

    private fun injectActivity() {
        (applicationContext as? BaseApplication)
            ?.injector
            ?.inject(this)
            ?: throw IllegalStateException("InjectedActivity should not be used without an Application that inherits from BaseApplication")
    }

    public override fun onNewIntent(intent: Intent) {
        setIntent(intent)
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                Log.d("Testing", query)
                searchPresenter.searchBooks(query)
            }
        }
    }

    override fun showBooks(bookList: List<BookResponse>) {
        if (bookList.isEmpty()) {
            vf_search_books.displayedChild = 1
        } else {
            vf_search_books.displayedChild = 0

        }
    }

}
