package szarch.bme.hu.ibdb.ui.base.comparators

import android.support.v7.util.DiffUtil
import szarch.bme.hu.ibdb.domain.models.Book

object BookComparator : DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }

}