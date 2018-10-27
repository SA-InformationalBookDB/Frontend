package szarch.bme.hu.ibdb.ui.main.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.ui.activities.ActivitiesFragment
import szarch.bme.hu.ibdb.ui.base.InjectedActivity
import szarch.bme.hu.ibdb.ui.favourites.FavouritesFragment
import szarch.bme.hu.ibdb.ui.main.fragment.MainFragment
import szarch.bme.hu.ibdb.ui.upload.UploadFragment
import szarch.bme.hu.ibdb.ui.users.UsersFragment
import szarch.bme.hu.ibdb.util.Navigator

class MainActivity : InjectedActivity() {

    private lateinit var fragment: Fragment

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_main -> {
                showFragment(MainFragment.TAG)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favourites -> {
                showFragment(FavouritesFragment.TAG)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_upload -> {
                showFragment(UploadFragment.TAG)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_activities -> {
                showFragment(ActivitiesFragment.TAG)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_users -> {
                showFragment(UsersFragment.TAG)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        showFragment(MainFragment.TAG)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_main_search -> {
                Navigator.navigateToSearchActivity(this@MainActivity)
                true
            }
            R.id.menu_main_account -> {
                Navigator.navigateToAccountActivity(this@MainActivity)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onBackPressed() {
        if (fragment.tag == MainFragment.TAG) {
            finish()
        } else {
            navigation.selectedItemId = R.id.navigation_main
        }
    }

    private fun showFragment(tag: String) {
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            when (tag) {
                MainFragment.TAG -> fragment =
                        MainFragment()
                FavouritesFragment.TAG -> fragment = FavouritesFragment()
                UploadFragment.TAG -> fragment = UploadFragment()
                ActivitiesFragment.TAG -> fragment = ActivitiesFragment()
                UsersFragment.TAG -> fragment = UsersFragment()
            }
        } else {
            fragment = supportFragmentManager.findFragmentByTag(tag)!!
        }
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment, tag).addToBackStack(null).commit()
    }
}