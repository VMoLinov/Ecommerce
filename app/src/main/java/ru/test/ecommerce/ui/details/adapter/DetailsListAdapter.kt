package ru.test.ecommerce.ui.details.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.test.ecommerce.utils.DetailsDiffUtilItemCallback
import ru.test.model.model.details.ColorItem
import ru.test.model.model.details.DetailsListItem

internal class DetailsListAdapter : AsyncListDifferDelegationAdapter<DetailsListItem>(
    DetailsDiffUtilItemCallback()
) {
    private var activeColor = 0
    private var activeMemory = 0

    fun initImages() {
        delegatesManager.addDelegate(DetailsScreenDelegates.productImagesDelegate())
    }

    fun initSpecs() {
        delegatesManager
            .addDelegate(DetailsScreenDelegates.specColorsDelegate(::onColorClick))
            .addDelegate(DetailsScreenDelegates.specMemoryDelegate(::onMemoryClick))
    }

    fun initActiveItems() {
        var colors = 0
        items.forEach { if (it is ColorItem) colors++ }
        activeMemory = colors
        items[activeColor].isActive = true
        items[activeMemory].isActive = true
        notifyItemChanged(activeColor)
        notifyItemChanged(activeMemory)
    }

    private fun onColorClick(position: Int) {
        items[activeColor].isActive = false
        items[position].isActive = true
        notifyItemChanged(activeColor)
        notifyItemChanged(position)
        activeColor = position
    }

    private fun onMemoryClick(position: Int) {
        items[activeMemory].isActive = false
        items[position].isActive = true
        notifyItemChanged(activeMemory)
        notifyItemChanged(position)
        activeMemory = position
    }

    override fun getItemId(position: Int): Long {
        return items[position].itemId
    }
}
