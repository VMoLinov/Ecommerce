package ru.test.ecommerce.utils

import androidx.recyclerview.widget.DiffUtil

open class DetailsDiffUtilItemCallback :
    DiffUtil.ItemCallback<ru.test.model.model.DetailsListItem>() {
    override fun areItemsTheSame(
        oldItem: ru.test.model.model.DetailsListItem,
        newItem: ru.test.model.model.DetailsListItem
    ): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(
        oldItem: ru.test.model.model.DetailsListItem,
        newItem: ru.test.model.model.DetailsListItem
    ): Boolean {
        return oldItem == newItem
    }
}

open class BasketDiffUtilItemCallback : DiffUtil.ItemCallback<ru.test.model.model.Basket>() {
    override fun areItemsTheSame(
        oldItem: ru.test.model.model.Basket,
        newItem: ru.test.model.model.Basket
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ru.test.model.model.Basket,
        newItem: ru.test.model.model.Basket
    ): Boolean {
        return oldItem == newItem
    }
}
