package szarch.bme.hu.ibdb.ui.users

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.layout_user_item.view.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.network.models.user.UserInfoResponse
import szarch.bme.hu.ibdb.ui.base.comparators.UserComparator


class UsersAdapter : ListAdapter<UserInfoResponse, UsersAdapter.UsersItemViewHolder>(UserComparator) {

    var listener: UserListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UsersItemViewHolder {
        val userItemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_user_item, viewGroup, false)
        return UsersItemViewHolder(userItemView)
    }

    override fun onBindViewHolder(holder: UsersItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvUserName.text = item.nickname
        holder.tvUserRole.text = item.role.name
        holder.tvUserEmail.text = item.email
        holder.ivUserMore.setOnClickListener {
            showUserInfoDialog(it, item)
        }

    }

    inner class UsersItemViewHolder(userItemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(userItemView) {
        val tvUserName = userItemView.tv_users_user_name
        val tvUserRole = userItemView.tv_users_user_role
        val tvUserEmail = userItemView.tv_users_user_email
        val ivUserMore = userItemView.iv_user_more
    }

    private fun showUserInfoDialog(view: View, user: UserInfoResponse) {
        val popup = PopupMenu(view.context, view)
        popup.menuInflater.inflate(R.menu.user_popup_menu, popup.menu)
        user.enabled?.let {
            if (it) {
                popup.menu.removeItem(R.id.menu_user_enable)
            } else {
                popup.menu.removeItem(R.id.menu_user_disable)
            }
        }

        popup.setOnMenuItemClickListener {
            Log.d("Testing", (listener == null).toString())
            when (it.itemId) {
                R.id.menu_user_enable -> {
                    listener?.enableUser(user.id)
                }
                R.id.menu_user_disable -> {
                    listener?.disableUSer(user.id)
                }

                R.id.menu_user_remove -> {
                    listener?.removeUser(user.id)
                }
            }
            true
        }
        popup.show()
    }

    interface UserListener {
        fun enableUser(userId: String)
        fun disableUSer(userId: String)
        fun removeUser(userId: String)
    }

}