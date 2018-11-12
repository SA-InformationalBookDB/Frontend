package szarch.bme.hu.ibdb.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_activities.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.models.Review
import szarch.bme.hu.ibdb.ui.base.BaseApplication
import javax.inject.Inject

class ActivitiesFragment : androidx.fragment.app.Fragment(), ActivitiesScreen {

    @Inject
    lateinit var activitiesPresenter: ActivitiesPresenter

    private lateinit var activitiesAdapter: ActivitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_activities, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activitiesPresenter.attachScreen(this)
        setupRecyclerViewAdapter()
        setupRecyclerView()
        getActivities()
    }

    override fun onDestroy() {
        activitiesPresenter.detachScreen()
        super.onDestroy()
    }

    private fun injectFragment() {
        (context?.applicationContext as? BaseApplication)
            ?.injector
            ?.inject(this)
            ?: throw IllegalStateException("InjectedFragment should not be used without an Application that inherits from BaseApplication")
    }

    private fun setupRecyclerViewAdapter() {
        activitiesAdapter = ActivitiesAdapter()
    }

    private fun setupRecyclerView() {
        rv_activities.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
        rv_activities.adapter = activitiesAdapter
    }


    private fun getActivities() {
        activitiesPresenter.getActivities()
    }

    override fun showActivities(reviewList: List<Review>) {
        activitiesAdapter.submitList(reviewList)
    }

    companion object {
        val TAG = ActivitiesFragment::class.java.simpleName
    }

}