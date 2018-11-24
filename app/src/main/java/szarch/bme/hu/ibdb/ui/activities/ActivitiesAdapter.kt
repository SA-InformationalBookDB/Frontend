package szarch.bme.hu.ibdb.ui.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.layout_activity_item.view.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.network.models.review.Review
import szarch.bme.hu.ibdb.ui.base.comparators.ReviewComparator

class ActivitiesAdapter : ListAdapter<Review, ActivitiesAdapter.ActivityItemViewHolder>(ReviewComparator) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ActivityItemViewHolder {
        val activityItemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_activity_item, viewGroup, false)
        return ActivityItemViewHolder(activityItemView)
    }

    override fun onBindViewHolder(holder: ActivityItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvActivityBookTitle.text = item.bookTitle
        holder.rtActivityRating.rating = item.points.toFloat()
        holder.tv_ActivityBookComment.text = item.comment

    }

    inner class ActivityItemViewHolder(activityItemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(activityItemView) {
        val tvActivityBookTitle = activityItemView.tv_activity_book_title
        val rtActivityRating = activityItemView.rt_activity_rating
        val tv_ActivityBookComment = activityItemView.tv_activity_book_comment

    }

}