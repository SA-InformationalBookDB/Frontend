package szarch.bme.hu.ibdb.ui.base.comparators

import android.support.v7.util.DiffUtil
import szarch.bme.hu.ibdb.domain.models.Review

object ReviewComparator : DiffUtil.ItemCallback<Review>() {

    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }

}