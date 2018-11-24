package szarch.bme.hu.ibdb.ui.users

import szarch.bme.hu.ibdb.network.models.user.UserInfo

interface UsersScreen {
    fun showUserList(userList: List<UserInfo>)
    fun showErrorMessage()
    fun showSuccessfulMessage()
}