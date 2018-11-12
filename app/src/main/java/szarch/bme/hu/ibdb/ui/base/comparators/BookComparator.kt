package szarch.bme.hu.ibdb.ui.base.comparators

import androidx.recyclerview.widget.DiffUtil
import szarch.bme.hu.ibdb.network.models.book.BookResponse

object BookComparator : DiffUtil.ItemCallback<BookResponse>() {

    override fun areItemsTheSame(oldItem: BookResponse, newItem: BookResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BookResponse, newItem: BookResponse): Boolean {
        return oldItem == newItem
    }

}