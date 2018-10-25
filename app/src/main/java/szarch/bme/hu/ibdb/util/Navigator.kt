package szarch.bme.hu.ibdb.util

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import szarch.bme.hu.ibdb.ui.account.AccountActivity
import szarch.bme.hu.ibdb.ui.search.SearchActivity

object Navigator {

    fun navigateToSearchActivity(activity: AppCompatActivity) {
        activity.startActivity(Intent(activity.applicationContext, SearchActivity::class.java))
    }

    fun navigateToAccountActivity(activity: AppCompatActivity) {
        activity.startActivity(Intent(activity.applicationContext, AccountActivity::class.java))
    }

}