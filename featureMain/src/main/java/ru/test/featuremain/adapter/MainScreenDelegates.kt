package ru.test.featuremain.adapter

import android.graphics.Paint
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.test.core.utils.ProminentLayoutManager
import ru.test.featuremain.databinding.*
import ru.test.model.model.main.BestSellerVerticalItemMain
import ru.test.model.model.main.CategoriesHorizontalItemMain
import ru.test.model.model.main.HotSalesHorizontalItemMain
import ru.test.model.model.main.MainListItem
import ru.test.model.model.main.BestSeller
import ru.test.model.model.main.Category
import ru.test.model.model.main.HomeStore

object MainScreenDelegates {

    private var activeCategoryPosition = 0

    fun horizontalCategoriesBlock(onCategoryClick: (position: Long) -> Unit) =
        adapterDelegateViewBinding<CategoriesHorizontalItemMain, MainListItem, ItemMainCategoriesBinding>(
            { inflater, container ->
                ItemMainCategoriesBinding.inflate(inflater, container, false)
            }) {
            var adapter: ListDelegationAdapter<List<MainListItem>>? = null
            fun onCategoryClick(position: Int) {
                adapter?.apply {
                    (items!![activeCategoryPosition] as Category).isActive = false
                    (items!![position] as Category).isActive = true
                    notifyItemChanged(activeCategoryPosition)
                    notifyItemChanged(position)
                    activeCategoryPosition = position
                    onCategoryClick(items!![position].itemId)
                }
            }
            adapter = ListDelegationAdapter(horizontalCategoriesItems { position ->
                onCategoryClick(position)
            })
            bind {
                item.categories[activeCategoryPosition].isActive = true
                binding.recyclerCategories.adapter = adapter
                binding.header.startField.text = getString(item.title)
                binding.header.endField.text = getString(item.endTitle)
                adapter.items = item.categories
            }
        }

    private fun horizontalCategoriesItems(onCategoryClick: (position: Int) -> Unit) =
        adapterDelegateViewBinding<Category, MainListItem, ItemRecycleCategoryBinding>(
            { inflater, container ->
                ItemRecycleCategoryBinding.inflate(inflater, container, false)
            }) {
            with(binding) {
                bind {
                    icon.isActivated = item.isActive
                    container.isActivated = item.isActive
                    name.isActivated = item.isActive
                    container.setOnClickListener {
                        onCategoryClick(adapterPosition)
                    }
                    icon.setImageDrawable(getDrawable(item.icon))
                    name.text = getString(item.name)
                }
            }
        }

    fun horizontalHotSalesBlock(glide: RequestManager, onHotSalesClick: (id: Long) -> Unit) =
        adapterDelegateViewBinding<HotSalesHorizontalItemMain, MainListItem, ItemMainHotSalesBinding>(
            { inflater, container -> ItemMainHotSalesBinding.inflate(inflater, container, false) }
        ) {
            with(binding) {
                recyclerHotSales.layoutManager =
                    ProminentLayoutManager(root.context)
                PagerSnapHelper().attachToRecyclerView(recyclerHotSales)
                val adapter = ListDelegationAdapter(horizontalHotSalesItems(glide, onHotSalesClick))
                recyclerHotSales.adapter = adapter
                bind {
                    adapter.items = item.items
                    header.startField.text = getString(item.title)
                    header.endField.text = getString(item.endTitle)
                }
            }
        }

    private fun horizontalHotSalesItems(
        glide: RequestManager, onHotSalesClick: (id: Long) -> Unit
    ) =
        adapterDelegateViewBinding<HomeStore, MainListItem, ItemRecycleHotSalesBinding>(
            { inflater, container ->
                ItemRecycleHotSalesBinding.inflate(inflater, container, false)
            }) {
            with(binding) {
                root.setOnClickListener { onHotSalesClick(item.id) }
                bind {
                    glide.load(item.picture).transform(CenterCrop(), RoundedCorners(40))
                        .into(image)
                    name.text = item.title
                    description.text = item.subtitle
                    isNew.visibility = if (item.isNew) View.VISIBLE else View.INVISIBLE
                }
            }
        }

    fun gridBestSellerBlock(glide: RequestManager, onBestSellerClick: (id: Long) -> Unit) =
        adapterDelegateViewBinding<BestSellerVerticalItemMain, MainListItem, ItemMainBestSellerBinding>(
            { inflater, container ->
                ItemMainBestSellerBinding.inflate(inflater, container, false)
            }) {
            val adapter = ListDelegationAdapter(gridBestSellerItems(glide, onBestSellerClick))
            binding.recyclerBestSeller.adapter = adapter
            bind {
                binding.header.startField.text = getString(item.title)
                binding.header.endField.text = getString(item.endTitle)
                adapter.items = item.items
            }
        }

    private fun gridBestSellerItems(glide: RequestManager, onBestSellerClick: (id: Long) -> Unit) =
        adapterDelegateViewBinding<BestSeller, MainListItem, ItemRecycleBestSellerBinding>(
            { inflater, container ->
                ItemRecycleBestSellerBinding.inflate(inflater, container, false)
            }) {
            with(binding) {
                root.setOnClickListener { onBestSellerClick(item.id) }
                bind {
                    glide.load(item.picture).into(image)
                    name.text = item.title
                    likeFill.isVisible = item.isFavorites
                    price.text = item.discountPrice
                    oldPrice.text = item.priceWithoutDiscount
                    oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    likeButton.setOnClickListener { likeFill.isVisible = !likeFill.isVisible }
                }
            }
        }
}
