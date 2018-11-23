package szarch.bme.hu.ibdb.ui.main.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_main_book_item.view.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.network.models.book.BookResponse
import szarch.bme.hu.ibdb.ui.base.comparators.BookComparator

class MainBookAdapter : ListAdapter<BookResponse, MainBookAdapter.BookItemViewHolder>(BookComparator) {

    var listener: Listener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BookItemViewHolder {
        val bookItemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_main_book_item, viewGroup, false)
        return BookItemViewHolder(bookItemView)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.item = item
        holder.tvBookTitle.text = item.title
        if (item.imageUrl != null && item.imageUrl.isNotEmpty()) {
            item.imageUrl.let {
                Picasso.get()
                    .load(it)
                    .fit()
                    .placeholder(R.drawable.ic_book_image_url)
                    .into(holder.ivBookImage)
            }
        }
    }

    inner class BookItemViewHolder(bookItemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(bookItemView) {
        val tvBookTitle = bookItemView.tv_main_book_item
        val ivBookImage = bookItemView.iv_main_book_item

        var item: BookResponse? = null

        init {
            bookItemView.setOnClickListener {
                item?.let { item ->
                    listener?.onItemSelected(item.id)
                }
            }
        }
    }

    interface Listener {
        fun onItemSelected(bookItemId: String)
    }

}