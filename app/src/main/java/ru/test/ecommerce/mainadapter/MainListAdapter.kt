package ru.test.ecommerce.mainadapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MainListAdapter(
) : AsyncListDifferDelegationAdapter<MainListItem>(MainDiffUtilItemCallback()) {

    init {
        delegatesManager
            .addDelegate(MainScreenDelegates.horizontalCategoriesBlock())
            .addDelegate(MainScreenDelegates.horizontalHotSalesBlock())
            .addDelegate(MainScreenDelegates.gridBestSellerBlock())
//            .addDelegate(MainScreenDelegates.wideProgressDelegate())
//            .addDelegate(MainScreenDelegates.thinProgressDelegate())
//            .addDelegate(MainScreenDelegates.wideErrorDelegate(onRefreshClick))
//            .addDelegate(MainScreenDelegates.thinErrorDelegate(onRefreshClick))
    }

    override fun getItemId(position: Int): Long {
        return items[position].itemId
    }
}