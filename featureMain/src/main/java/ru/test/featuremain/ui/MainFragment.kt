package ru.test.featuremain.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import ru.test.core.utils.RequestKeys
import ru.test.core.App
import ru.test.featuremain.adapter.MainListAdapter
import ru.test.core.ui.BaseFragment
import ru.test.core.utils.viewBinding
import ru.test.featuremain.R
import ru.test.featuremain.databinding.FragmentMainBinding
import ru.test.featuremain.viewmodel.MainViewModel

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding by viewBinding { FragmentMainBinding.bind(it) }
    private val viewModel by lazy {
        ViewModelProvider(this, App.getComponent().viewModel())[MainViewModel::class.java]
    }
    private val adapter by lazy {
        MainListAdapter(
            glide = Glide.with(this),
            reselectCategory = viewModel::filterCategory,
            onHotSalesClick = ::hotSalesToast,
            onBestSellerClick = ::onBestSellerClick
        )
    }

    private fun onBestSellerClick(id: Long) {
        requireActivity().supportFragmentManager.setFragmentResult(
            RequestKeys.OPEN_DETAILS.key, Bundle().apply {
                putLong(RequestKeys.OPEN_DETAILS.key, id)
            })
    }

    private fun hotSalesToast(id: Long) {
        Toast.makeText(context, "CLick id: $id", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainRecycler.adapter = adapter
        binding.mainHeader.filter.setOnClickListener {
            BottomSheet().show(requireActivity().supportFragmentManager, "TAG")
        }
        viewModel.data.collectWhileStarted { adapter.items = it }
        parentFragmentManager.setFragmentResultListener(REQUEST_FILTER, this, resultListener)
        parentFragmentManager.setFragmentResultListener(ADD_TO_CART, this, resultListener)
    }

    private val resultListener = FragmentResultListener { requestKey, result ->
        when (requestKey) {
            REQUEST_FILTER -> {
                viewModel.filterBottom(result.getIntArray(FILTER_POSITIONS) ?: intArrayOf())
            }
        }
    }

    companion object {
        const val ADD_TO_CART = "cart"
        const val REQUEST_FILTER = "filter"
        const val FILTER_POSITIONS = "filter positions"
    }
}
