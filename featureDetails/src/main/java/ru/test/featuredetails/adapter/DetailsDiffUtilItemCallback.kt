package ru.test.featuredetails.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.test.model.model.details.DetailsListItem

open class DetailsDiffUtilItemCallback :
    DiffUtil.ItemCallback<DetailsListItem>() {
    override fun areItemsTheSame(
        oldItem: DetailsListItem, newItem: DetailsListItem
    ): Boolean = oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(
        oldItem: DetailsListItem, newItem: DetailsListItem
    ): Boolean = oldItem == newItem
}
