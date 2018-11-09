package szarch.bme.hu.ibdb.ui.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.models.Book
import szarch.bme.hu.ibdb.ui.base.BaseApplication
import javax.inject.Inject


class SearchActivity : AppCompatActivity(), SearchScreen {

    @Inject
    lateinit var searchPresenter: SearchPresenter

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
                doSearch(query)
            }
        }
    }

    private fun doSearch(query: String) {
        Log.d("Testing", query)
        searchPresenter.searchBooks(query)
    }

    override fun showBooks(bookList: List<Book>) {

    }

}
