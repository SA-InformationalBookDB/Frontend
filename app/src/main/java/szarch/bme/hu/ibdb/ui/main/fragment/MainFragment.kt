package szarch.bme.hu.ibdb.ui.main.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_main.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.models.Book
import szarch.bme.hu.ibdb.ui.base.InjectedFragment

class MainFragment : InjectedFragment(), MainBookAdapter.Listener, MainScreen {


    private lateinit var favouriteAdapter: MainBookAdapter
    private lateinit var recommendationAdapter: MainBookAdapter
    private lateinit var bestSellerAdapter: MainBookAdapter
    private lateinit var popularAdapter: MainBookAdapter
    private lateinit var trendingAdapter: MainBookAdapter
    private lateinit var mainScreenPresenter: MainScreenPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Dagger injection required
        mainScreenPresenter = MainScreenPresenter()
        mainScreenPresenter.attachScreen(this)
        setupRecyclerViewAdapter()
        setupRecyclerView()
        getBooks()
    }

    override fun onDestroy() {
        mainScreenPresenter.detachScreen()
        super.onDestroy()
    }

    private fun setupRecyclerViewAdapter() {
        favouriteAdapter = MainBookAdapter()
        recommendationAdapter = MainBookAdapter()
        bestSellerAdapter = MainBookAdapter()
        popularAdapter = MainBookAdapter()
        trendingAdapter = MainBookAdapter()
        favouriteAdapter.listener = this
        recommendationAdapter.listener = this
        bestSellerAdapter.listener = this
        popularAdapter.listener = this
        trendingAdapter.listener = this
    }

    private fun setupRecyclerView() {
        rv_main_favourites.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv_main_favourites.adapter = favouriteAdapter
        rv_main_recommendation.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv_main_recommendation.adapter = recommendationAdapter
        rv_main_bestsellers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv_main_bestsellers.adapter = bestSellerAdapter
        rv_main_popular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv_main_popular.adapter = popularAdapter
        rv_main_trending.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv_main_trending.adapter = trendingAdapter
    }

    private fun getBooks() {
        mainScreenPresenter.getFavouriteBooks()
        mainScreenPresenter.getRecommendationBooks()
        mainScreenPresenter.getBestsellerBooks()
        mainScreenPresenter.getPopularBooks()
        mainScreenPresenter.getTrendingBooks()
    }

    override fun showFavouriteBooks(bookList: List<Book>) {
        favouriteAdapter.submitList(bookList)
    }

    override fun showRecommendationBooks(bookList: List<Book>) {
        recommendationAdapter.submitList(bookList)
    }

    override fun showBestsellerBooks(bookList: List<Book>) {
        bestSellerAdapter.submitList(bookList)
    }

    override fun showPopularBooks(bookList: List<Book>) {
        popularAdapter.submitList(bookList)
    }

    override fun showTrendingBooks(bookList: List<Book>) {
        trendingAdapter.submitList(bookList)
    }

    override fun onItemSelected(bookItemId: String) {
        Toast.makeText(requireContext(), bookItemId, Toast.LENGTH_LONG).show()
    }

    companion object {
        val TAG = MainFragment::class.java.simpleName
    }

}