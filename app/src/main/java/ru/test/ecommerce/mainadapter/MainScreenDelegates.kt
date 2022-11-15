package ru.test.ecommerce.mainadapter

import android.graphics.Paint
import androidx.core.view.isVisible
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.test.ecommerce.bestseller.BestSellerItemMain
import ru.test.ecommerce.bestseller.BestSellerVerticalItemMain
import ru.test.ecommerce.category.CategoriesHorizontalItemMain
import ru.test.ecommerce.category.CategoryItemMain
import ru.test.ecommerce.databinding.*
import ru.test.ecommerce.hotsales.HotSaleItemMain
import ru.test.ecommerce.hotsales.HotSalesHorizontalItemMain

object MainScreenDelegates {

    fun horizontalCategoriesBlock() =
        adapterDelegateViewBinding<CategoriesHorizontalItemMain, MainListItem, ItemMainCategoriesBinding>(
            { inflater, container ->
                ItemMainCategoriesBinding.inflate(inflater, container, false)
            }) {
            var activeItem = 0
            var adapter: ListDelegationAdapter<List<MainListItem>>? = null
            fun notifyAdapter(activeItem: Int, onCLickItem: Int) {
                adapter?.let {
                    it.notifyItemChanged(activeItem)
                    it.notifyItemChanged(onCLickItem)
                }
            }
            adapter = ListDelegationAdapter(horizontalCategoriesItems { onCLickItem ->
                item.categories[activeItem].isActive = false
                item.categories[onCLickItem].isActive = true
                notifyAdapter(activeItem, onCLickItem)
                activeItem = onCLickItem
            })
            bind {
                binding.recyclerCategories.adapter = adapter
                binding.header.startField.text = getString(item.title)
                binding.header.endField.text = getString(item.endTitle)
                item.categories[activeItem].isActive = true
                adapter.items = item.categories
            }
        }

    private fun horizontalCategoriesItems(onClick: (Int) -> Unit) =
        adapterDelegateViewBinding<CategoryItemMain, MainListItem, ItemRecycleCategoryBinding>(
            { inflater, container ->
                ItemRecycleCategoryBinding.inflate(inflater, container, false)
            }) {
            bind {
                binding.icon.isActivated = item.isActive
                binding.container.isActivated = item.isActive
                binding.name.isActivated = item.isActive
                binding.container.setOnClickListener {
                    onClick(absoluteAdapterPosition)
                }
                binding.icon.setImageDrawable(getDrawable(item.icon))
                binding.name.text = getString(item.name)
            }
        }

    fun horizontalHotSalesBlock() =
        adapterDelegateViewBinding<HotSalesHorizontalItemMain, MainListItem, ItemMainHotSalesBinding>(
            { inflater, container -> ItemMainHotSalesBinding.inflate(inflater, container, false) }
        ) {
            binding.recyclerHotSales.layoutManager = ProminentLayoutManager(binding.root.context)
            PagerSnapHelper().attachToRecyclerView(binding.recyclerHotSales)
            val adapter = ListDelegationAdapter(horizontalHotSalesItems())
            binding.recyclerHotSales.adapter = adapter
            bind {
                adapter.items = item.categories
                binding.header.startField.text = item.title
                binding.header.endField.text = item.endTitle
            }
        }

    private fun horizontalHotSalesItems() =
        adapterDelegateViewBinding<HotSaleItemMain, MainListItem, ItemRecycleHotSalesBinding>(
            { inflater, container ->
                ItemRecycleHotSalesBinding.inflate(inflater, container, false)
            }) {
            bind {
                Glide.with(binding.root)
                    .load("https://static.digit.in/default/942998b8b4d3554a6259aeb1a0124384dbe0d4d5.jpeg")
                    .transform(RoundedCorners(40))
                    .into(binding.image)
            }
        }

    fun gridBestSellerBlock() =
        adapterDelegateViewBinding<BestSellerVerticalItemMain, MainListItem, ItemMainBestSellerBinding>(
            { inflater, container ->
                ItemMainBestSellerBinding.inflate(inflater, container, false)
            }) {
            val adapter = ListDelegationAdapter(gridBestSellerItems())
            binding.recyclerBestSeller.adapter = adapter
            bind {
                binding.header.startField.text = item.title
                binding.header.endField.text = item.endTitle
                adapter.items = item.categories
            }
        }

    private fun gridBestSellerItems() =
        adapterDelegateViewBinding<BestSellerItemMain, MainListItem, ItemRecycleBestSellerBinding>(
            { inflater, container ->
                ItemRecycleBestSellerBinding.inflate(inflater, container, false)
            }) {
            bind {
                binding.likeFill.isVisible = item.isFavorites
                binding.name.text = item.title
                binding.price.text = item.price
                binding.oldPrice.text = item.oldPrice
                binding.oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding.likeButton.setOnClickListener {
                    binding.likeFill.isVisible = !binding.likeFill.isVisible
                }
            }
        }
}
