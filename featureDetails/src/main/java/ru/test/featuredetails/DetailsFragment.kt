package ru.test.featuredetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.PagerSnapHelper
import ru.test.core.App
import ru.test.core.ui.BaseFragment
import ru.test.core.ui.States
import ru.test.core.utils.ProminentLayoutManager
import ru.test.core.utils.RequestKeys
import ru.test.core.utils.viewBinding
import ru.test.domain.detailsscreen.model.DeviceDetails
import ru.test.featuredetails.adapter.DetailsListAdapter
import ru.test.featuredetails.databinding.FragmentDetailsBinding

class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val binding: FragmentDetailsBinding by viewBinding { FragmentDetailsBinding.bind(it) }
    private val adapterImages = DetailsListAdapter().apply { initImages() }
    private val adapterSpec = DetailsListAdapter().apply { initSpecs() }
    private val viewModel by lazy {
        ViewModelProvider(this, App.getComponent().viewModel())[DetailsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.details.collectWhileStarted {
            when (it) {
                is States.Success -> {
                    loading(false)
                    adapterImages.items = it.data.images
                    adapterSpec.items = it.data.specs
                    adapterSpec.initActiveItems()
                    initFields(it.data)
                }
                is States.Loading -> loading(true)
                is States.Error -> showError(it.error)
            }
        }
    }

    private fun loading(isVisible: Boolean) = run { binding.loading.isVisible = isVisible }

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
