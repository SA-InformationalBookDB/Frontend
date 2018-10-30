package szarch.bme.hu.ibdb.ui.users

import szarch.bme.hu.ibdb.domain.models.User
import szarch.bme.hu.ibdb.ui.base.Presenter

class UsersPresenter : Presenter<UsersScreen>() {

    fun getUsers() {
        screen?.showUserList(getDemoUserList())
    }

    private fun getDemoUserList(): List<User> {


        val userList: MutableList<User> = arrayListOf()
        for (i in 0..10) {
            userList.add(
                i, User(
                    id = i.toString(),
                    email = "dmeo@email.com",
                    nickName = "Nickname $i",
                    password = "pass",
                    dateOfBirth = null
                )
            )
        }
        return userList
    }

}