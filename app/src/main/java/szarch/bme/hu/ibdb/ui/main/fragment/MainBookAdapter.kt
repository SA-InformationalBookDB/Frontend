package szarch.bme.hu.ibdb.ui.main.fragment

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_main_book_item.view.*
import szarch.bme.hu.ibdb.R
import szarch.bme.hu.ibdb.domain.models.Book
import szarch.bme.hu.ibdb.ui.base.BookComparator

class MainBookAdapter : ListAdapter<Book, MainBookAdapter.BookItemViewHolder>(BookComparator) {

    var listener: Listener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BookItemViewHolder {
        val bookItemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_main_book_item, viewGroup, false)
        return BookItemViewHolder(bookItemView)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvBookTitle.text = item.title
    }

    inner class BookItemViewHolder(bookItemView: View) : RecyclerView.ViewHolder(bookItemView) {
        val tvBookTitle = bookItemView.tv_main_book_item
        val ivBookImage = bookItemView.iv_main_book_item

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