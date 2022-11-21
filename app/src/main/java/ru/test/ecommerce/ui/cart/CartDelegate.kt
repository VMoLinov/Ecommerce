package ru.test.ecommerce.ui.cart

import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.test.ecommerce.databinding.ItemRecycleCartBinding
import ru.test.core.toPrice

object CartDelegate {

    fun productImagesDelegate(
        glide: RequestManager,
        onMinusClick: (Long) -> Unit,
        onPlusClick: (Long) -> Unit,
        onDeleteClick: (Long) -> Unit
    ) = adapterDelegateViewBinding<ru.test.model.model.Basket, ru.test.model.model.Basket, ItemRecycleCartBinding>(
        { inflater, container ->
            ItemRecycleCartBinding.inflate(inflater, container, false)
        }) {
        binding.trash.setOnClickListener { onDeleteClick(item.id) }
        binding.minus.setOnClickListener { onMinusClick(item.id) }
        binding.plus.setOnClickListener { onPlusClick(item.id) }
        bind {
            glide.load(item.images).transform(CenterCrop(), RoundedCorners(20)).into(binding.image)
            binding.title.text = item.title
            binding.price.text = (item.priceNum * item.quantity).toPrice(2)
            binding.quantity.text = item.quantity.toString()
        }
    }
}
