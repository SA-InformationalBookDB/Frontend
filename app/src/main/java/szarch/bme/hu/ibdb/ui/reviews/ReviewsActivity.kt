package szarch.bme.hu.ibdb.ui.reviews

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_reviews.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.network.models.review.ReviewResponse
import szarch.bme.hu.ibdb.ui.base.BaseApplication
import javax.inject.Inject

class ReviewsActivity : AppCompatActivity(), ReviewsScreen {

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

    override fun showReviews(reviewsResponse: List<ReviewResponse>) {
        reviewAdapter.submitList(reviewsResponse)
        if (reviewsResponse.isEmpty()) {
            vf_reviews.displayedChild = 1
        } else {
            vf_reviews.displayedChild = 0
        }
    }

    override fun showSuccessfulReviewSending() {
        Toast.makeText(this, resources.getString(R.string.successful_review), Toast.LENGTH_LONG).show()
        reviewPresenter.getReviews(bookId)
    }

    override fun showUnsuccessfulReviewSending() {
        reviewPresenter.getReviews(bookId)
    }

    companion object {
        const val INTENT_BOOK_ID = "bookID"
    }

}
