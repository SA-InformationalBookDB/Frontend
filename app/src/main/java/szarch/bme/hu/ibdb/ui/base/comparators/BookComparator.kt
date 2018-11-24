package szarch.bme.hu.ibdb.ui.base.comparators

import androidx.recyclerview.widget.DiffUtil
import szarch.bme.hu.ibdb.network.models.book.Book

object BookComparator : DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }

}