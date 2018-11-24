package szarch.bme.hu.ibdb.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import szarch.bme.hu.ibdb.ui.account.AccountActivity
import szarch.bme.hu.ibdb.ui.detail.DetailActivity
import szarch.bme.hu.ibdb.ui.reviews.ReviewsActivity

object Navigator {

    fun navigateToAccountActivity(activity: AppCompatActivity) {
        activity.startActivityForResult(
            Intent(activity.applicationContext, AccountActivity::class.java),
            szarch.bme.hu.ibdb.ui.main.activity.MainActivity.ACCOUNT_REQUEST_CODE
        )
    }

    fun navigateToReviewActivity(activity: AppCompatActivity, bookId: String) {
        val intent = Intent(activity, ReviewsActivity::class.java)
        intent.putExtra(ReviewsActivity.INTENT_BOOK_ID, bookId)
        activity.startActivityForResult(intent, DetailActivity.REVIEW_REQUEST_CODE)
    }

    fun navigateToDetailScreen(activity: FragmentActivity, bookId: String) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.INTENT_BOOK_ID, bookId)
        activity.startActivity(intent)
    }

}