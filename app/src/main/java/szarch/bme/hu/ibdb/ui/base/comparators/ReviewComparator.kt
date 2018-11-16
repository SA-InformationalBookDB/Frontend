package szarch.bme.hu.ibdb.ui.base.comparators

import androidx.recyclerview.widget.DiffUtil
import szarch.bme.hu.ibdb.network.models.review.ReviewResponse

object ReviewComparator : DiffUtil.ItemCallback<ReviewResponse>() {

    override fun areItemsTheSame(oldItem: ReviewResponse, newItem: ReviewResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ReviewResponse, newItem: ReviewResponse): Boolean {
        return oldItem == newItem
    }

}