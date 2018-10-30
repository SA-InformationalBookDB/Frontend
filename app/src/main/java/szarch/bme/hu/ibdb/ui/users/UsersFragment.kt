package szarch.bme.hu.ibdb.ui.users

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_users.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.models.User
import szarch.bme.hu.ibdb.ui.base.InjectedFragment

class UsersFragment : InjectedFragment(), UsersScreen {

    private lateinit var usersAdapter: UsersAdapter
    private lateinit var usersPresenter: UsersPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersPresenter = UsersPresenter()
        usersPresenter.attachScreen(this)
        setupRecyclerViewAdapter()
        setupRecyclerView()
        getUsers()
    }

    override fun onDestroy() {
        usersPresenter.detachScreen()
        super.onDestroy()
    }

    private fun setupRecyclerViewAdapter() {
        usersAdapter = UsersAdapter()
    }

    private fun setupRecyclerView() {
        rv_users.layoutManager = LinearLayoutManager(requireContext())
        rv_users.adapter = usersAdapter
    }

    private fun getUsers() {
        usersPresenter.getUsers()
    }

    override fun showUserList(userList: List<User>) {
        usersAdapter.submitList(userList)
    }

    companion object {
        val TAG = UsersFragment::class.java.simpleName
    }

}
