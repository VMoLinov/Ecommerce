package ru.test.ecommerce.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.test.ecommerce.R
import ru.test.ecommerce.bestseller.BestSellerVerticalItemMain
import ru.test.ecommerce.category.CategoriesHorizontalItemMain
import ru.test.ecommerce.databinding.FragmentMainBinding
import ru.test.ecommerce.hotsales.HotSalesHorizontalItemMain
import ru.test.ecommerce.mainadapter.MainListItem
import ru.test.ecommerce.mainadapter.viewBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding { FragmentMainBinding.bind(it) }
    private val viewModel by viewModels<MainViewModel>()
    private val adapter by lazy {
        MainListAdapter(
            glide = Glide.with(this),
            onCategoryClick = viewModel::filterCategory,
            onHotSalesClick = ::hotSalesToast,
            onBestSellerClick = ::onBestSellerClick
        )
    }

    private fun onBestSellerClick(id: Long) {

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
        binding.nav.bottomNavigationView.getOrCreateBadge(R.id.menuCart).apply {
            number = 1
            isVisible = true
        }
        viewModel.data.collectWhileStarted {
            adapter.items = listOf<MainListItem>(
                CategoriesHorizontalItemMain(),
                HotSalesHorizontalItemMain(it.home_store),
                BestSellerVerticalItemMain(it.best_seller)
            )
        }
        parentFragmentManager.setFragmentResultListener("Bottom", this, resultListener)
    }

    private val resultListener = FragmentResultListener { requestKey, result ->
        when (requestKey) {
            "Bottom" -> {
                val str = result.getInt("Select", -1)
                Toast.makeText(context, "Brand pos: $str\nPrice pos: ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun <T> Flow<T>.collectWhileStarted(block: (T) -> Unit) {
        launchAndRepeatOnStart {
            collect { block.invoke(it) }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

fun Fragment.launchAndRepeatOnStart(block: suspend () -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            block.invoke()
        }
    }
}
