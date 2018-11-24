package szarch.bme.hu.ibdb.ui.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.layout_favourite_book_item.view.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.network.models.book.Book
import szarch.bme.hu.ibdb.ui.base.comparators.BookComparator

class FavouriteAdapter : ListAdapter<Book, FavouriteAdapter.BookItemViewHolder>(BookComparator) {

    var listener: Listener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BookItemViewHolder {
        val bookItemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_favourite_book_item, viewGroup, false)
        return BookItemViewHolder(bookItemView)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.item = item
        holder.tvBookTitle.text = item.title
        holder.tvBookAuthor.text = item.author
        holder.rtBookRating.rating = (item.averageRating!! % 5.0).toFloat()
        holder.tvBookPosition.text = position.toString()

    }

    inner class BookItemViewHolder(bookItemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(bookItemView) {
        val tvBookTitle = bookItemView.tv_favourites_book_title
        val tvBookAuthor = bookItemView.tv_favourites_book_author
        val rtBookRating = bookItemView.rt_favourite_rating
        val tvBookPosition = bookItemView.tv_favourites_book_position
        val ivBookImage = bookItemView.iv_favourites_book_item

        var item: Book? = null

        init {
            itemView.setOnClickListener {
                item?.let { item -> listener?.onItemSelected(item.id) }
            }
        }
    }

    interface Listener {
        fun onItemSelected(bookItemId: String)
    }

}