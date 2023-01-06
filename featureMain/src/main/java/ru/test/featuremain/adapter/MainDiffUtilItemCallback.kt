package ru.test.featuremain.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.test.domain.mainscreen.model.MainListItem

open class MainDiffUtilItemCallback : DiffUtil.ItemCallback<MainListItem>() {
    override fun areItemsTheSame(oldItem: MainListItem, newItem: MainListItem): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(oldItem: MainListItem, newItem: MainListItem): Boolean {
        return oldItem == newItem
    }
}
