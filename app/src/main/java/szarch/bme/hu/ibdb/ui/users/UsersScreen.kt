package szarch.bme.hu.ibdb.ui.users

import szarch.bme.hu.ibdb.network.models.user.UserInfoResponse

interface UsersScreen {
    fun showUserList(userList: List<UserInfoResponse>)
    fun showErrorMessage()
    fun showSuccessfulMessage()
}