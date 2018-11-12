package szarch.bme.hu.ibdb.ui.base.comparators

import androidx.recyclerview.widget.DiffUtil
import szarch.bme.hu.ibdb.domain.models.User

object UserComparator : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}