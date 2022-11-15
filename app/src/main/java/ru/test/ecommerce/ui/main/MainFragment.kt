package ru.test.ecommerce.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.test.ecommerce.R
import ru.test.ecommerce.adapter.CategoriesAdapter
import ru.test.ecommerce.adapter.viewBinding
import ru.test.ecommerce.bestseller.BestSellerItem
import ru.test.ecommerce.bestseller.BestSellerVerticalItem
import ru.test.ecommerce.category.CategoriesHorizontalItem
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
        binding.mainHeader.filter.setOnClickListener {
            parentFragmentManager.setFragmentResultListener(
                "requestKey",
                this
            ) { requestKey, bundle ->
                val result = bundle.getString(requestKey)
                Toast.makeText(context, "Result", Toast.LENGTH_SHORT).show()
            }
            BottomSheet().show(requireActivity().supportFragmentManager, "TAG")
        }
        initAdapter()
    }

    private fun initAdapter() {
        adapter.apply {
            items = listOf(
                CategoriesHorizontalItem(),
                HotSalesHorizontalItem(
                    title = "Hot sales",
                    endTitle = "see more",
                    categories = IntRange(1, 4).map { HotSaleItem("Item $it") }
                ),
                BestSellerVerticalItem(
                    title = "Best Seller",
                    endTitle = "see more",
                    categories = IntRange(1, 6).map {
                        BestSellerItem(
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
