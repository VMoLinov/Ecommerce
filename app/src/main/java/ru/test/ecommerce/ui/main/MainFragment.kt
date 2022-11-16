package ru.test.ecommerce.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import ru.test.ecommerce.R
import ru.test.ecommerce.bestseller.BestSellerItemMain
import ru.test.ecommerce.bestseller.BestSellerVerticalItemMain
import ru.test.ecommerce.category.CategoriesHorizontalItemMain
import ru.test.ecommerce.databinding.FragmentMainBinding
import ru.test.ecommerce.hotsales.HotSaleItemMain
import ru.test.ecommerce.hotsales.HotSalesHorizontalItemMain
import ru.test.ecommerce.mainadapter.MainListAdapter
import ru.test.ecommerce.mainadapter.viewBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding { FragmentMainBinding.bind(it) }
    private lateinit var adapter: MainListAdapter

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
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
        initAdapter()
    }

    private val resultListener = FragmentResultListener { requestKey, result ->
        when (requestKey) {
//            ...
        }
        TODO("Not yet implemented")
    }

    private fun initAdapter() {
        adapter.apply {
            items = listOf(
                CategoriesHorizontalItemMain(),
                HotSalesHorizontalItemMain(
                    title = "Hot sales",
                    endTitle = "see more",
                    categories = IntRange(1, 4).map { HotSaleItemMain("Item $it") }
                ),
                BestSellerVerticalItemMain(
                    title = "Best Seller",
                    endTitle = "see more",
                    categories = IntRange(1, 6).map {
                        BestSellerItemMain(
                            it.toLong(),
                            "Samsung Galaxy s20 Ultra",
                            "$1,500",
                            "$1,047",
                            true,
                            "https://mi92.ru/wp-content/uploads/2020/03/smartfon-xiaomi-mi-10-pro-12-256gb-global-version-starry-blue-sinij-1.jpg"
                        )
                    }
                ),
            )
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
