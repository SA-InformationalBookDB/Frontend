package szarch.bme.hu.ibdb.ui.activities

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_activity_item.view.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.models.Review
import szarch.bme.hu.ibdb.ui.base.comparators.ReviewComparator

class ActivitiesAdapter : ListAdapter<Review, ActivitiesAdapter.ActivityItemViewHolder>(ReviewComparator) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ActivityItemViewHolder {
        val activityItemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_activity_item, viewGroup, false)
        return ActivityItemViewHolder(activityItemView)
    }

    override fun onBindViewHolder(holder: ActivityItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvActivityBookTitle.text = item.book.title
        Log.d("Testing", (item.book.views % 5.0).toFloat().toString())
        holder.rtActivityRating.rating = (item.book.views % 5.0).toFloat()
        holder.tv_ActivityBookComment.text = item.comment

    }

    inner class ActivityItemViewHolder(activityItemView: View) : RecyclerView.ViewHolder(activityItemView) {
        val ivBookImage = activityItemView.iv_activity_book_item
        val tvActivityBookTitle = activityItemView.tv_activity_book_title
        val rtActivityRating = activityItemView.rt_activity_rating
        val tv_ActivityBookComment = activityItemView.tv_activity_book_comment

    }

}