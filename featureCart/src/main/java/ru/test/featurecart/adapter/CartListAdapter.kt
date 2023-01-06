package ru.test.featurecart.adapter

import com.bumptech.glide.RequestManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.test.domain.cartscreen.model.Basket

class CartListAdapter(
    glide: RequestManager,
    onMinusClick: (Long) -> Unit,
    onPlusClick: (Long) -> Unit,
    onDeleteClick: (Long) -> Unit
) : AsyncListDifferDelegationAdapter<Basket>(BasketDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(
            CartDelegate.productImagesDelegate(glide, onMinusClick, onPlusClick, onDeleteClick)
        )
    }
}
