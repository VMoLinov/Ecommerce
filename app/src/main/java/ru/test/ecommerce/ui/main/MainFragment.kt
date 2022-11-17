package ru.test.ecommerce.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import ru.test.ecommerce.R
import ru.test.ecommerce.databinding.FragmentMainBinding
import ru.test.ecommerce.ui.details.DetailsFragment
import ru.test.ecommerce.ui.main.adapter.MainListAdapter
import ru.test.ecommerce.utils.getAppComponent
import ru.test.ecommerce.utils.viewBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding by viewBinding { FragmentMainBinding.bind(it) }
    private val viewModel by lazy {
        ViewModelProvider(this, getAppComponent().viewModel())[MainViewModel::class.java]
    }
    private val adapter by lazy {
        MainListAdapter(
            glide = Glide.with(this),
            onCategoryClick = viewModel::filterCategory,
            onHotSalesClick = ::hotSalesToast,
            onBestSellerClick = ::onBestSellerClick
        )
    }

    private fun onBestSellerClick(id: Long) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container, DetailsFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
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
            ADD_TO_CART -> {
                binding.nav.bottomNavigationView.getOrCreateBadge(R.id.menuCart).apply {
                    number++
                    isVisible = true
                }
            }
        }
    }

    companion object {
        const val ADD_TO_CART = "cart"
        const val REQUEST_FILTER = "filter"
        const val FILTER_POSITIONS = "filter positions"
        fun newInstance() = MainFragment()
    }
}
