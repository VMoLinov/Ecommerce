package ru.test.ecommerce.mainadapter

import androidx.recyclerview.widget.DiffUtil
import ru.test.ecommerce.detailsadapter.DetailsListItem

open class MainDiffUtilItemCallback : DiffUtil.ItemCallback<MainListItem>() {
    override fun areItemsTheSame(oldItem: MainListItem, newItem: MainListItem): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(oldItem: MainListItem, newItem: MainListItem): Boolean {
        return oldItem == newItem
    }
}

open class DetailsDiffUtilItemCallback : DiffUtil.ItemCallback<DetailsListItem>() {
    override fun areItemsTheSame(oldItem: DetailsListItem, newItem: DetailsListItem): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(oldItem: DetailsListItem, newItem: DetailsListItem): Boolean {
        return oldItem == newItem
    }
}
