package ru.test.featuredetails.adapter

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.test.featuredetails.databinding.ItemRecycleImagesBinding
import ru.test.featuredetails.databinding.ItemRecycleSpecColorBinding
import ru.test.featuredetails.databinding.ItemRecycleSpecMemoryBinding
import ru.test.model.model.details.ColorItem
import ru.test.model.model.details.ImageItem
import ru.test.model.model.details.MemoryItem
import ru.test.model.model.details.DetailsListItem

object DetailsScreenDelegates {

    fun productImagesDelegate() =
        adapterDelegateViewBinding<ImageItem, DetailsListItem, ItemRecycleImagesBinding>(
            { inflater, container ->
                ItemRecycleImagesBinding.inflate(inflater, container, false)
            }) {
            bind {
                Glide.with(binding.root)
                    .load(item.url)
                    .transform(FitCenter(), RoundedCorners(40))
                    .into(binding.image)
            }
        }

    fun specColorsDelegate(onColorClick: (Int) -> Unit) =
        adapterDelegateViewBinding<ColorItem, DetailsListItem, ItemRecycleSpecColorBinding>(
            { inflater, container ->
                ItemRecycleSpecColorBinding.inflate(inflater, container, false)
            }) {
            binding.root.setOnClickListener {
                onColorClick(adapterPosition)
            }
            bind {
                binding.isSelect.isVisible = item.isActive
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

    fun specMemoryDelegate(onMemoryClick: (Int) -> Unit) =
        adapterDelegateViewBinding<MemoryItem, DetailsListItem, ItemRecycleSpecMemoryBinding>(
            { inflater, container ->
                ItemRecycleSpecMemoryBinding.inflate(inflater, container, false)
            }) {
            binding.root.setOnClickListener {
                onMemoryClick(adapterPosition)
            }
            bind {
                binding.shape.isActivated = item.isActive
                binding.title.isActivated = item.isActive
                binding.title.text = item.size
            }
        }
}
