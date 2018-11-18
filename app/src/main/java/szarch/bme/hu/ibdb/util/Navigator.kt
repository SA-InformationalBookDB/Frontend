package szarch.bme.hu.ibdb.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import szarch.bme.hu.ibdb.ui.account.AccountActivity
import szarch.bme.hu.ibdb.ui.detail.DetailActivity
import szarch.bme.hu.ibdb.ui.reviews.ReviewsActivity

object Navigator {

    fun navigateToAccountActivity(activity: AppCompatActivity) {
        activity.startActivity(Intent(activity.applicationContext, AccountActivity::class.java))
    }

    fun navigateToReviewActivity(activity: AppCompatActivity, bookId: String) {
        val intent = Intent(activity, ReviewsActivity::class.java)
        intent.putExtra("bookId", bookId)
        activity.startActivity(intent)
    }

    fun navigateToDetailScreen(activity: FragmentActivity, bookId: String) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("bookId", bookId)
        activity.startActivity(intent)
    }

}