package szarch.bme.hu.ibdb.ui.users

import szarch.bme.hu.ibdb.domain.models.User

interface UsersScreen {
    fun showUserList(userList: List<User>)
}