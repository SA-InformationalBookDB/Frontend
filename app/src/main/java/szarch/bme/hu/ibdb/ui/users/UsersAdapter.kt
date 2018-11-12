package szarch.bme.hu.ibdb.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.layout_user_item.view.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.models.User
import szarch.bme.hu.ibdb.ui.base.comparators.UserComparator


class UsersAdapter : ListAdapter<User, UsersAdapter.UsersItemViewHolder>(UserComparator) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UsersItemViewHolder {
        val userItemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_user_item, viewGroup, false)
        return UsersItemViewHolder(userItemView)
    }

    override fun onBindViewHolder(holder: UsersItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvUserName.text = item.nickName
        holder.tvUserRole.text = item.role.name
        holder.tvUserEmail.text = item.email

    }

    inner class UsersItemViewHolder(userItemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(userItemView) {
        val ivUserImage = userItemView.iv_users_user_item
        val tvUserName = userItemView.tv_users_user_name
        val tvUserRole = userItemView.tv_users_user_role
        val tvUserEmail = userItemView.tv_users_user_email

    }

}