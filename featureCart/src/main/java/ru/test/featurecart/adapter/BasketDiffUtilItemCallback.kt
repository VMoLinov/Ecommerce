package ru.test.featurecart.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.test.domain.cartscreen.model.Basket

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
