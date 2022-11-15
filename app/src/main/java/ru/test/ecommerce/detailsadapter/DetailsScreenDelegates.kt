package ru.test.ecommerce.detailsadapter

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
import ru.test.ecommerce.databinding.ItemRecyclerImagesBinding

object DetailsScreenDelegates {

    fun productImagesDelegate() =
        adapterDelegateViewBinding<ImageItem, DetailsListItem, ItemRecyclerImagesBinding>(
            { inflater, container ->
                ItemRecyclerImagesBinding.inflate(inflater, container, false)
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
            colorsList.first().isVisible = true
            binding.root.setOnClickListener {
                colorsList.forEach { it.isVisible = false }
                binding.isSelect.isVisible = true
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

    private val colorsList = mutableListOf<ImageView>()

    fun specMemoryDelegate() =
        adapterDelegateViewBinding<MemoryItem, DetailsListItem, ItemRecycleSpecMemoryBinding>(
            { inflater, container ->
                ItemRecycleSpecMemoryBinding.inflate(inflater, container, false)
            }) {
            memoryListShape.add(binding.shape)
            memoryListText.add(binding.title)
            memoryListShape.first().isActivated = true
            memoryListText.first().isActivated = true
            binding.root.setOnClickListener {
                memoryListShape.forEach { it.isActivated = false }
                memoryListText.forEach { it.isActivated = false }
                binding.title.isActivated = true
                binding.shape.isActivated = true
            }
            bind {
                binding.title.text = item.size
            }
        }

    private val memoryListShape = mutableListOf<FrameLayout>()
    private val memoryListText = mutableListOf<TextView>()
}
