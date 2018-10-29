package szarch.bme.hu.ibdb.ui.favourites

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_favourites.*
import szarch.bme.hu.ibdb.domain.models.Book
import szarch.bme.hu.ibdb.ui.base.InjectedFragment

class FavouritesFragment : InjectedFragment(), FavouriteAdapter.Listener, FavouriteScreen {

    private lateinit var favouriteAdapter: FavouriteAdapter
    private lateinit var favouritePresenter: FavouritePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favourites, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Dagger injection required
        favouritePresenter = FavouritePresenter()
        favouritePresenter.attachScreen(this)
        setupRecyclerViewAdapter()
        setupRecyclerView()
        getBooks()
    }

    private fun setupRecyclerViewAdapter() {
        favouriteAdapter = FavouriteAdapter()
        favouriteAdapter.listener = this
    }

    private fun setupRecyclerView() {
        rv_favourite_favourites.layoutManager = LinearLayoutManager(requireContext())
        rv_favourite_favourites.adapter = favouriteAdapter
    }

    private fun getBooks() {
        favouritePresenter.getFavouriteBooks()
    }

    override fun onDestroy() {
        favouritePresenter.detachScreen()
        super.onDestroy()
    }

    override fun showFavouritesBooks(bookList: List<Book>) {
        favouriteAdapter.submitList(bookList)
    }

    override fun onItemSelected(bookItemId: String) {
        Toast.makeText(requireContext(), bookItemId, Toast.LENGTH_LONG).show()
    }

    companion object {
        val TAG = FavouritesFragment::class.java.simpleName
    }

}