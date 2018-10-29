package szarch.bme.hu.ibdb.ui.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_activities.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.models.Review
import szarch.bme.hu.ibdb.ui.base.InjectedFragment

class ActivitiesFragment : InjectedFragment(), ActivitiesScreen {

    private lateinit var activitiesAdapter: ActivitiesAdapter
    private lateinit var activitiesPresenter: ActivitiesPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_activities, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activitiesPresenter = ActivitiesPresenter()
        activitiesPresenter.attachScreen(this)
        setupRecyclerViewAdapter()
        setupRecyclerView()
        getActivities()
    }

    override fun onDestroy() {
        activitiesPresenter.detachScreen()
        super.onDestroy()
    }

    private fun setupRecyclerViewAdapter() {
        activitiesAdapter = ActivitiesAdapter()
    }

    private fun setupRecyclerView() {
        rv_activities.layoutManager = LinearLayoutManager(requireContext())
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