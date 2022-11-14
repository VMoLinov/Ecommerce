package ru.test.ecommerce.adapter


import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class CategoriesAdapter(
) : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager
            .addDelegate(MainScreenDelegates.horizontalCategoriesBlock())
            .addDelegate(MainScreenDelegates.horizontalHotSalesBlock())
            .addDelegate(MainScreenDelegates.verticalBestSellersBlock())
//            .addDelegate(MainScreenDelegates.wideProgressDelegate())
//            .addDelegate(MainScreenDelegates.thinProgressDelegate())
//            .addDelegate(MainScreenDelegates.wideErrorDelegate(onRefreshClick))
//            .addDelegate(MainScreenDelegates.thinErrorDelegate(onRefreshClick))
    }

    override fun getItemId(position: Int): Long {
        return items[position].itemId
    }
}