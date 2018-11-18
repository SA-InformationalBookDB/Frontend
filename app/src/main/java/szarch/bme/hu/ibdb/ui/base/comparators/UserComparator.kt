package szarch.bme.hu.ibdb.ui.base.comparators

import androidx.recyclerview.widget.DiffUtil
import szarch.bme.hu.ibdb.network.models.user.UserInfoResponse

object UserComparator : DiffUtil.ItemCallback<UserInfoResponse>() {

    override fun areItemsTheSame(oldItem: UserInfoResponse, newItem: UserInfoResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserInfoResponse, newItem: UserInfoResponse): Boolean {
        return oldItem == newItem
    }

}