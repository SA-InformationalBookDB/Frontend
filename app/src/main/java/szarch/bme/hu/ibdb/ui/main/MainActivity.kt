package szarch.bme.hu.ibdb.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.ui.base.InjectedActivity

class MainActivity : InjectedActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favourites -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_activities -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
