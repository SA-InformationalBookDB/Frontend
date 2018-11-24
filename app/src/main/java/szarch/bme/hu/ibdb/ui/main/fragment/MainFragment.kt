package szarch.bme.hu.ibdb.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.local.SharedPreferencesProvider
import szarch.bme.hu.ibdb.network.models.book.Book
import szarch.bme.hu.ibdb.ui.base.BaseApplication
import szarch.bme.hu.ibdb.util.Navigator
import szarch.bme.hu.ibdb.util.StringUtil
import java.util.*
import javax.inject.Inject

class MainFragment : androidx.fragment.app.Fragment(), MainBookAdapter.Listener, MainScreen {

    @Inject
    lateinit var mainScreenPresenter: MainScreenPresenter

    @Inject
    lateinit var sharedPreferencesProvider: SharedPreferencesProvider

    private lateinit var recommendationAdapter: MainBookAdapter
    private lateinit var bestSellerAdapter: MainBookAdapter
    private lateinit var popularAdapter: MainBookAdapter
    private lateinit var trendingAdapter: MainBookAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainScreenPresenter.attachScreen(this)
        setupRecyclerViewAdapter()
        setupRecyclerView()
        shouldShowLoginDialog()
        getBooks()
        checkUserValidity()
    }

    override fun onDestroy() {
        mainScreenPresenter.detachScreen()
        super.onDestroy()
    }


    private fun checkUserValidity() {
        mainScreenPresenter.getUserIsAdmin()
    }


    private fun injectFragment() {
        (context?.applicationContext as? BaseApplication)
            ?.injector
            ?.inject(this)
            ?: throw IllegalStateException("InjectedFragment should not be used without an Application that inherits from BaseApplication")
    }

    private fun setupRecyclerViewAdapter() {
        recommendationAdapter = MainBookAdapter()
        bestSellerAdapter = MainBookAdapter()
        popularAdapter = MainBookAdapter()
        trendingAdapter = MainBookAdapter()
        recommendationAdapter.listener = this
        bestSellerAdapter.listener = this
        popularAdapter.listener = this
        trendingAdapter.listener = this
    }

    private fun setupRecyclerView() {
        rv_main_recommendation.layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(
                    requireContext(),
                    androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
                    false
                )
        rv_main_recommendation.adapter = recommendationAdapter
        rv_main_bestsellers.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            requireContext(),
            androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
            false
        )
        rv_main_bestsellers.adapter = bestSellerAdapter
        rv_main_popular.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            requireContext(),
            androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
            false
        )
        rv_main_popular.adapter = popularAdapter
        rv_main_trending.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            requireContext(),
            androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
            false
        )
        rv_main_trending.adapter = trendingAdapter
    }

    private fun getBooks() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -3)
        mainScreenPresenter.getRecommendationBooks(StringUtil.formatTrendingDateToString(calendar.time))
        mainScreenPresenter.getBestsellerBooks()
        mainScreenPresenter.getPopularBooks()
        mainScreenPresenter.getTrendingBooks(StringUtil.formatTrendingDateToString(calendar.time))
    }

    private fun shouldShowLoginDialog() {
        if (sharedPreferencesProvider.getIsClientFirstStarting()) {
            val alertDialogBuilder =
                AlertDialog.Builder(ContextThemeWrapper(requireContext(), R.style.PreferenceScreen))
            alertDialogBuilder.setTitle(R.string.first_starting_information_title)
                .setMessage(R.string.first_starting_information_text)
                .setPositiveButton(R.string.dialog_ok_text) { dialogInterface, i ->
                    sharedPreferencesProvider.setClientFirstStarting(false)
                    dialogInterface.dismiss()
                }
                .show()
        }
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
        Navigator.navigateToDetailScreen(requireActivity(), bookItemId)
    }

    override fun setUserInteractionPossibilities(userIsAdmin: Boolean) {
        activity?.navigation?.menu?.getItem(2)?.isVisible = userIsAdmin
        activity?.navigation?.menu?.getItem(4)?.isVisible = userIsAdmin
    }

    override fun hideRecommendationBookList() {
        cv_recommendations.isVisible = false
    }

    override fun showErrorMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    companion object {
        val TAG = MainFragment::class.java.simpleName
    }

}