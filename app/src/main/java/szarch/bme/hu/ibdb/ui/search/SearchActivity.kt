package szarch.bme.hu.ibdb.ui.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import szarch.bme.hu.ibdb.domain.models.Book


class SearchActivity : AppCompatActivity(), SearchScreen {

    lateinit var searchPresenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchPresenter = SearchPresenter()
    }

    override fun onStart() {
        super.onStart()
        searchPresenter.attachScreen(this)
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
