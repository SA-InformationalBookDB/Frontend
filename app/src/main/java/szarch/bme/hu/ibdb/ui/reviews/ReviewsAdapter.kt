package szarch.bme.hu.ibdb.ui.reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.layout_review_item.view.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.network.models.review.Review
import szarch.bme.hu.ibdb.ui.base.comparators.ReviewComparator

class ReviewsAdapter : ListAdapter<Review, ReviewsAdapter.ReviewItemViewHolder>(ReviewComparator) {

    var listener: Listener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ReviewItemViewHolder {
        val bookItemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_review_item, viewGroup, false)
        return ReviewItemViewHolder(bookItemView)
    }

    override fun onBindViewHolder(holder: ReviewItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvNickName.text = item.userNickName
        holder.rtBookRating.rating = (item.points).toFloat()
        holder.tvReviewComment.text = item.comment
        holder.ivReviewMore.setOnClickListener {
            showreviewInfoDialog(holder.ivReviewMore, item)
        }
    }

    inner class ReviewItemViewHolder(reviewItemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(reviewItemView) {
        val tvNickName = reviewItemView.tv_review_nick_name
        val rtBookRating = reviewItemView.rt_review_rating
        val tvReviewComment = reviewItemView.tv_review_book_comment
        val ivReviewMore = reviewItemView.iv_review_more

    }

    private fun showreviewInfoDialog(view: View, review: Review) {
        val popup = PopupMenu(view.context, view)
        popup.menuInflater.inflate(R.menu.review_popup_menu, popup.menu)

        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_review_remove -> {
                    listener?.removeReview(review.id)
                }
            }
            true
        }
        popup.show()
    }

    interface Listener {
        fun removeReview(reviewId: String)
    }

}