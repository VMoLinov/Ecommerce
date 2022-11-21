package ru.test.ecommerce.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.PagerSnapHelper
import ru.test.ecommerce.R
import ru.test.ecommerce.databinding.FragmentDetailsBinding
import ru.test.ecommerce.ui.details.adapter.DetailsListAdapter
import ru.test.core.ui.BaseFragment
import ru.test.core.viewBinding
import ru.test.core.di.App
import ru.test.core.ProminentLayoutManager
import ru.test.core.RequestKeys
import ru.test.model.model.DeviceDetails

class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val binding: FragmentDetailsBinding by viewBinding { FragmentDetailsBinding.bind(it) }
    private val adapterImages = DetailsListAdapter().apply { initImages() }
    private val adapterSpec = DetailsListAdapter().apply { initSpecs() }
    private val viewModel by lazy {
        ViewModelProvider(this, App.getComponent().viewModel())[DetailsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.images.collectWhileStarted {
            it?.let {
                adapterImages.items = it.images
                adapterSpec.items = it.specs
                adapterSpec.initActiveItems()
                initFields(it)
            }
        }
    }

    private fun initFields(data: DeviceDetails) = with(binding) {
        recyclerImages.layoutManager = ProminentLayoutManager(root.context)
        PagerSnapHelper().attachToRecyclerView(recyclerImages)
        recyclerImages.adapter = adapterImages
        recyclerChooser.adapter = adapterSpec
        name.text = data.title
        rating.rating = data.rating
        processor.image.setImageResource(R.drawable.ic_processor)
        camera.image.setImageResource(R.drawable.ic_camera)
        ram.image.setImageResource(R.drawable.ic_ram)
        external.image.setImageResource(R.drawable.ic_external)
        processor.title.text = data.CPU
        camera.title.text = data.camera
        ram.title.text = data.ssd
        external.title.text = data.sd
        price.text = data.price
        clickListeners(data)
    }

    private fun FragmentDetailsBinding.clickListeners(data: DeviceDetails) {
        buttonBack.setOnClickListener { setFragmentResultBackStack() }
        buttonCart.setOnClickListener {
            //TODO check cart before open
        }
        buttonAddToCart.setOnClickListener {
            requireActivity().supportFragmentManager.setFragmentResult(
                RequestKeys.ADD_TO_CART.key,
                Bundle().apply { putString("", data.id) }
            )
            Toast.makeText(context, "Item add to cart!", Toast.LENGTH_SHORT).show()
        }
    }
}
