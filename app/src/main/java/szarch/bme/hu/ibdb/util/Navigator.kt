package szarch.bme.hu.ibdb.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import szarch.bme.hu.ibdb.ui.account.AccountActivity
import szarch.bme.hu.ibdb.ui.detail.DetailActivity
import szarch.bme.hu.ibdb.ui.search.SearchActivity

object Navigator {

    fun navigateToSearchActivity(activity: AppCompatActivity) {
        activity.startActivity(Intent(activity.applicationContext, SearchActivity::class.java))
    }

    fun navigateToAccountActivity(activity: AppCompatActivity) {
        activity.startActivity(Intent(activity.applicationContext, AccountActivity::class.java))
    }

    fun navigateToDetailScreen(activity: FragmentActivity, bookId: String) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("bookId", bookId)
        activity.startActivity(intent)
    }

}