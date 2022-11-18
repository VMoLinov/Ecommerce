package ru.test.ecommerce.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import ru.test.ecommerce.R
import ru.test.ecommerce.databinding.FragmentCartBinding
import ru.test.ecommerce.ui.main.BaseFragment
import ru.test.ecommerce.utils.getAppComponent
import ru.test.ecommerce.utils.viewBinding

class CartFragment : BaseFragment(R.layout.fragment_cart) {

    private val binding by viewBinding { FragmentCartBinding.bind(it) }
    private val viewModel by lazy {
        ViewModelProvider(this, getAppComponent().viewModel())[CartViewModel::class.java]
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
            adapter.items = cart?.basket
            adapter.notifyDataSetChanged()
            binding.total.text = cart?.total
        }
    }

    companion object {
        fun newInstance(): CartFragment = CartFragment()
    }
}
