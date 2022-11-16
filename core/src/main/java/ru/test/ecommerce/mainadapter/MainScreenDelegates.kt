package ru.test.ecommerce.mainadapter

import android.graphics.Paint
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.test.ecommerce.bestseller.BestSellerVerticalItemMain
import ru.test.ecommerce.category.CategoriesHorizontalItemMain
import ru.test.ecommerce.category.CategoryItemMain
import ru.test.ecommerce.databinding.*
import ru.test.ecommerce.hotsales.HotSalesHorizontalItemMain
import ru.test.ecommerce.repository.model.BestSellerDTO
import ru.test.ecommerce.repository.model.HomeStoreDTO

object MainScreenDelegates {

    fun horizontalCategoriesBlock(onCategoryClick: (name: String) -> Unit) =
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
            adapter =
                ListDelegationAdapter(horizontalCategoriesItems(onCategoryClick) { reselect ->
                    item.categories[activeItem].isActive = false
                    item.categories[reselect].isActive = true
                    notifyAdapter(activeItem, reselect)
                    activeItem = reselect
                })
            bind {
                binding.recyclerCategories.adapter = adapter
                binding.header.startField.text = getString(item.title)
                binding.header.endField.text = getString(item.endTitle)
                item.categories[activeItem].isActive = true
                adapter.items = item.categories
            }
        }

    private fun horizontalCategoriesItems(
        onCategoryClick: (name: String) -> Unit,
        reselect: (Int) -> Unit
    ) =
        adapterDelegateViewBinding<CategoryItemMain, MainListItem, ItemRecycleCategoryBinding>(
            { inflater, container ->
                ItemRecycleCategoryBinding.inflate(inflater, container, false)
            }) {
            with(binding) {
                bind {
                    icon.isActivated = item.isActive
                    container.isActivated = item.isActive
                    name.isActivated = item.isActive
                    container.setOnClickListener {
                        reselect(absoluteAdapterPosition)
                        onCategoryClick(getString(item.name))
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
        glide: RequestManager,
        onHotSalesClick: (id: Long) -> Unit
    ) =
        adapterDelegateViewBinding<HomeStoreDTO, MainListItem, ItemRecycleHotSalesBinding>(
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
                    isNew.visibility = if (item.is_new) View.VISIBLE else View.INVISIBLE
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
        adapterDelegateViewBinding<BestSellerDTO, MainListItem, ItemRecycleBestSellerBinding>(
            { inflater, container ->
                ItemRecycleBestSellerBinding.inflate(inflater, container, false)
            }) {
            with(binding) {
                root.setOnClickListener { onBestSellerClick(item.id) }
                bind {
                    glide.load(item.picture).into(image)
                    name.text = item.title
                    likeFill.isVisible = item.is_favorites
                    price.text = item.discount_price.toString()
                    oldPrice.text = item.price_without_discount.toString()
                    oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    likeButton.setOnClickListener { likeFill.isVisible = !likeFill.isVisible }
                }
            }
        }
}
