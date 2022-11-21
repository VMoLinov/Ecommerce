package ru.test.ecommerce.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import ru.test.core.RequestKeys
import ru.test.ecommerce.R
import ru.test.ecommerce.databinding.FragmentCartBinding
import ru.test.core.ui.BaseFragment
import ru.test.core.viewBinding
import ru.test.core.di.App

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
            if (cart?.basket?.isEmpty() == true) setFragmentResultEmptyCart()
        }
        binding.buttonBack.setOnClickListener { setFragmentResultBackStack() }
        binding.checkout.setOnClickListener {
            setFragmentResultEmptyCart()
            setFragmentResultBackStack()
        }
    }

    private fun setFragmentResultEmptyCart() {
        requireActivity().supportFragmentManager.setFragmentResult(
            RequestKeys.CLEAR_CART.key,
            Bundle()
        )
    }
}
