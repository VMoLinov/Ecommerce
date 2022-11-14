package ru.test.ecommerce.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.test.ecommerce.R
import ru.test.ecommerce.adapter.*
import ru.test.ecommerce.bestseller.BestSellerVerticalItem
import ru.test.ecommerce.category.CategoriesHorizontalItem
import ru.test.ecommerce.category.CategoryItem
import ru.test.ecommerce.databinding.FragmentMainBinding
import ru.test.ecommerce.hotsales.HotSaleItem
import ru.test.ecommerce.hotsales.HotSalesHorizontalItem

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding { FragmentMainBinding.bind(it) }
    private val adapter = CategoriesAdapter()

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainRecycler.adapter = adapter
        adapter.apply {
            items = listOf(
                CategoriesHorizontalItem(),
                HotSalesHorizontalItem(
                    title = "Hot sales",
                    endTitle = "see more",
                    categories = IntRange(1, 4).map { HotSaleItem("Item $it") }
                ),
                BestSellerVerticalItem(
                    title = "Best sales",
                    categories = IntRange(1, 20).map {
                        CategoryItem(R.string.ic_phones, R.drawable.ic_phones)
                    }
                ),
            )
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
