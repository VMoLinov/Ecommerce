package ru.test.ecommerce.ui.details.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.test.ecommerce.utils.DetailsDiffUtilItemCallback

internal class DetailsListAdapter : AsyncListDifferDelegationAdapter<DetailsListItem>(
    DetailsDiffUtilItemCallback()
) {

    fun initImages() {
        delegatesManager.addDelegate(DetailsScreenDelegates.productImagesDelegate())
    }

    fun initSpecs() {
        delegatesManager
            .addDelegate(DetailsScreenDelegates.specColorsDelegate())
            .addDelegate(DetailsScreenDelegates.specMemoryDelegate())
    }

    override fun getItemId(position: Int): Long {
        return items[position].itemId
    }
}
