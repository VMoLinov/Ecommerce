package ru.test.ecommerce.ui.main.adapter

import com.bumptech.glide.RequestManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.test.ecommerce.utils.MainDiffUtilItemCallback

class MainListAdapter(
    val glide: RequestManager,
    val onCategoryClick: (name: String) -> Unit,
    val onHotSalesClick: (id: Long) -> Unit,
    val onBestSellerClick: (id: Long) -> Unit
) : AsyncListDifferDelegationAdapter<MainListItem>(MainDiffUtilItemCallback()) {

    init {
        delegatesManager
            .addDelegate(MainScreenDelegates.horizontalCategoriesBlock(onCategoryClick))
            .addDelegate(MainScreenDelegates.horizontalHotSalesBlock(glide, onHotSalesClick))
            .addDelegate(MainScreenDelegates.gridBestSellerBlock(glide, onBestSellerClick))
    }

    override fun getItemId(position: Int): Long {
        return items[position].itemId
    }
}
