package ru.test.featuremain.adapter

import com.bumptech.glide.RequestManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.test.model.MainListItem

class MainListAdapter(
    val glide: RequestManager,
    val reselectCategory: (id: Long) -> Unit,
    val onHotSalesClick: (id: Long) -> Unit,
    val onBestSellerClick: (id: Long) -> Unit
) : AsyncListDifferDelegationAdapter<MainListItem>(MainDiffUtilItemCallback()) {

    init {
        delegatesManager
            .addDelegate(MainScreenDelegates.horizontalCategoriesBlock(reselectCategory))
            .addDelegate(MainScreenDelegates.horizontalHotSalesBlock(glide, onHotSalesClick))
            .addDelegate(MainScreenDelegates.gridBestSellerBlock(glide, onBestSellerClick))
    }

    override fun getItemId(position: Int): Long {
        return items[position].itemId
    }
}
