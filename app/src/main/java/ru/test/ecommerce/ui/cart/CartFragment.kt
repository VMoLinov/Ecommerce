package ru.test.ecommerce.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import ru.test.ecommerce.R
import ru.test.ecommerce.databinding.FragmentCartBinding
import ru.test.core.ui.BaseFragment
import ru.test.core.viewBinding
import ru.test.ecommerce.App
import ru.test.ecommerce.ui.main.MainFragment

class CartFragment : BaseFragment(R.layout.fragment_cart) {

    private val binding by viewBinding { FragmentCartBinding.bind(it) }
    private val viewModel by lazy {
        ViewModelProvider(this, App.getComponent().viewModel())[CartViewModel::class.java]
    }
    private val adapter by lazy {
        CartListAdapter(
            Glide.with(binding.root),
            viewModel::minus,
            viewModel::plus,
            viewModel::delete
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerCart.adapter = adapter
        viewModel.data.collectWhileStarted { cart ->
            binding.checkout.isEnabled = cart?.basket?.isEmpty() == false
            adapter.items = cart?.basket
            adapter.notifyDataSetChanged()
            binding.total.text = cart?.total
            binding.delivery.text = cart?.delivery
        }
        binding.buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.checkout.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                MainFragment.CLEAR_CART, // Put data
                Bundle().apply { putString(MainFragment.CLEAR_CART, "Checkout complete!") }
            )
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        fun newInstance(): CartFragment = CartFragment()
    }
}
