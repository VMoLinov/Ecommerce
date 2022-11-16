package ru.test.ecommerce.ui.details.adapter

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.test.ecommerce.databinding.ItemRecycleSpecColorBinding
import ru.test.ecommerce.databinding.ItemRecycleSpecMemoryBinding
import ru.test.ecommerce.databinding.ItemRecycleImagesBinding
import ru.test.ecommerce.ui.details.adapter.items.ColorItem
import ru.test.ecommerce.ui.details.adapter.items.ImageItem
import ru.test.ecommerce.ui.details.adapter.items.MemoryItem

object DetailsScreenDelegates {

    fun productImagesDelegate() =
        adapterDelegateViewBinding<ImageItem, DetailsListItem, ItemRecycleImagesBinding>(
            { inflater, container ->
                ItemRecycleImagesBinding.inflate(inflater, container, false)
            }) {

            bind {
                Glide.with(binding.root)
                    .load(item.url)
                    .transform(FitCenter())
                    .into(binding.image)
            }
        }

    fun specColorsDelegate() =
        adapterDelegateViewBinding<ColorItem, DetailsListItem, ItemRecycleSpecColorBinding>(
            { inflater, container ->
                ItemRecycleSpecColorBinding.inflate(inflater, container, false)
            }) {
            colorsList.add(binding.isSelect)
            colorsList[0].isVisible = true
            binding.root.setOnClickListener {
                if (absoluteAdapterPosition != colorSelected) {
                    colorsList[colorSelected].isVisible = false
                    colorSelected = absoluteAdapterPosition
                    colorsList[colorSelected].isVisible = true
                }
            }
            bind {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    binding.shape.background.colorFilter =
                        BlendModeColorFilter(Color.parseColor(item.color), BlendMode.SRC_ATOP)
                } else {
                    @Suppress("DEPRECATION")
                    binding.shape.background
                        .setColorFilter(Color.parseColor(item.color), PorterDuff.Mode.SRC_ATOP)
                }
            }
        }

    private var colorSelected = 0
    private val colorsList = mutableListOf<ImageView>()

    fun specMemoryDelegate() =
        adapterDelegateViewBinding<MemoryItem, DetailsListItem, ItemRecycleSpecMemoryBinding>(
            { inflater, container ->
                ItemRecycleSpecMemoryBinding.inflate(inflater, container, false)
            }) {
            memoryListShape.add(binding.shape)
            memoryListText.add(binding.title)
            changeActivated(0, true)
            binding.root.setOnClickListener {
                val realPosition = absoluteAdapterPosition - colorsList.size
                if (memorySelected != realPosition) {
                    changeActivated(memorySelected, false)
                    memorySelected = realPosition
                    changeActivated(memorySelected, true)
                }
            }
            bind {
                binding.title.text = item.size
            }
        }

    private fun changeActivated(position: Int, visibility: Boolean) {
        memoryListShape[position].isActivated = visibility
        memoryListText[position].isActivated = visibility
    }

    private var memorySelected = 0
    private val memoryListShape = mutableListOf<FrameLayout>()
    private val memoryListText = mutableListOf<TextView>()
}
