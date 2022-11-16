package ru.test.ecommerce.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import ru.test.ecommerce.R
import ru.test.ecommerce.databinding.FragmentDetailsBinding
import ru.test.ecommerce.detailsadapter.ColorItem
import ru.test.ecommerce.detailsadapter.DetailsListAdapter
import ru.test.ecommerce.detailsadapter.ImageItem
import ru.test.ecommerce.detailsadapter.MemoryItem
import ru.test.ecommerce.mainadapter.ProminentLayoutManager
import ru.test.ecommerce.mainadapter.viewBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val binding: FragmentDetailsBinding by viewBinding { FragmentDetailsBinding.bind(it) }
    private val adapterSpec = DetailsListAdapter().apply { initSpecs() }
    private val adapterImages = DetailsListAdapter().apply { initImages() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterSpec.items = listOf(
            ColorItem("#772D03"),
            ColorItem("#010035"),
            MemoryItem("256 GB"),
            MemoryItem("128 GB")
        )
        adapterImages.items = listOf(
            ImageItem("https://avatars.mds.yandex.net/get-mpic/5235334/img_id5575010630545284324.png/orig"),
            ImageItem("https://www.manualspdf.ru/thumbs/products/l/1260237-samsung-galaxy-note-20-ultra.jpg")
        )
        binding.recyclerImages.layoutManager = ProminentLayoutManager(binding.root.context)
        PagerSnapHelper().attachToRecyclerView(binding.recyclerImages)
        binding.recyclerImages.adapter = adapterImages
        binding.recyclerChooser.adapter = adapterSpec
        binding.name.text = "Galaxy Note 20 Ultra"
        binding.rating.rating = 4.5f
        binding.processor.image.setImageResource(R.drawable.ic_processor)
        binding.camera.image.setImageResource(R.drawable.ic_camera)
        binding.ram.image.setImageResource(R.drawable.ic_ram)
        binding.external.image.setImageResource(R.drawable.ic_external)
        binding.price.text = "$1,500.00"
    }
}
