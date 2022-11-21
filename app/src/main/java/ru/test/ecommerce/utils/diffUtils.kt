package ru.test.ecommerce.utils

import androidx.recyclerview.widget.DiffUtil
import ru.test.model.model.cart.Basket
import ru.test.model.model.details.DetailsListItem

open class DetailsDiffUtilItemCallback :
    DiffUtil.ItemCallback<DetailsListItem>() {
    override fun areItemsTheSame(
        oldItem: DetailsListItem,
        newItem: DetailsListItem
    ): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(
        oldItem: DetailsListItem,
        newItem: DetailsListItem
    ): Boolean {
        return oldItem == newItem
    }
}

open class BasketDiffUtilItemCallback : DiffUtil.ItemCallback<Basket>() {
    override fun areItemsTheSame(
        oldItem: Basket,
        newItem: Basket
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Basket,
        newItem: Basket
    ): Boolean {
        return oldItem == newItem
    }
}
