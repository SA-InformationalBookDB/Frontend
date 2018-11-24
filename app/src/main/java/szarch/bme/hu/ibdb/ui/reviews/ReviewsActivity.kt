package szarch.bme.hu.ibdb.ui.reviews

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_reviews.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.network.models.review.Review
import szarch.bme.hu.ibdb.ui.base.BaseApplication
import javax.inject.Inject

class ReviewsActivity : AppCompatActivity(), ReviewsScreen, ReviewsAdapter.Listener {

    @Inject
    lateinit var reviewPresenter: ReviewsPresenter

    private lateinit var reviewAdapter: ReviewsAdapter

    private lateinit var bookId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectActivity()
        setContentView(R.layout.activity_reviews)
        setupRecyclerView()
        setupCommentButton()
    }

    override fun onStart() {
        super.onStart()
        bookId = intent.getStringExtra(INTENT_BOOK_ID)
        reviewPresenter.attachScreen(this)
        reviewPresenter.getReviews(bookId)
    }

    private fun injectActivity() {
        (applicationContext as? BaseApplication)
            ?.injector
            ?.inject(this)
            ?: throw IllegalStateException("InjectedActivity should not be used without an Application that inherits from BaseApplication")
    }

    private fun setupRecyclerView() {
        reviewAdapter = ReviewsAdapter()
        reviewAdapter.listener = this
        rv_reviews.layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(
                    this,
                    RecyclerView.VERTICAL,
                    false
                )
        rv_reviews.adapter = reviewAdapter
    }

    private fun setupCommentButton() {
        fab.setOnClickListener { view ->
            showReviewDialog()
        }
    }

    private fun showReviewDialog() {
        val dialogView = layoutInflater.inflate(R.layout.layout_comment_dialog, null)
        dialogView.findViewById<AppCompatRatingBar>(R.id.rt_review_upload)
            .setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                if (rating < 1.0) {
                    ratingBar.rating = 1.0f
                }
            }
        val dialog = AlertDialog.Builder(ContextThemeWrapper(this, R.style.PreferenceScreen))
            .setView(dialogView)
            .setTitle(R.string.review_dialog_title)
            .setPositiveButton(R.string.send_text) { dialog, which ->
                reviewPresenter.sendReview(
                    bookId,
                    dialogView.findViewById<androidx.appcompat.widget.AppCompatRatingBar>(R.id.rt_review_upload).rating.toDouble(),
                    dialogView.findViewById<EditText>(R.id.et_review_upload_comment).text.toString()
                )
            }
            .create()

        dialog.show()
    }

    override fun showReviews(reviews: List<Review>) {
        reviewAdapter.submitList(reviews)
        if (reviews.isEmpty()) {
            vf_reviews.displayedChild = 1
        } else {
            vf_reviews.displayedChild = 0
        }
    }

    override fun showActionResult(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        reviewPresenter.getReviews(bookId)
    }

    override fun removeReview(reviewId: String) {
        reviewPresenter.removeReview(reviewId)
    }

    companion object {
        const val INTENT_BOOK_ID = "bookID"
    }

}
