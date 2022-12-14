package ru.test.featurecart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import ru.test.core.App
import ru.test.core.ui.BaseFragment
import ru.test.core.ui.States
import ru.test.core.utils.RequestKeys
import ru.test.core.utils.viewBinding
import ru.test.featurecart.adapter.CartListAdapter
import ru.test.featurecart.databinding.FragmentCartBinding

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
        viewModel.data.collectWhileStarted {
            when (it) {
                is States.Success -> {
                    loading(false)
                    binding.checkout.isEnabled = it.data?.basket?.isEmpty() == false
                    adapter.items = it.data?.basket
                    adapter.notifyDataSetChanged()
                    binding.total.text = it.data?.total
                    binding.delivery.text = it.data?.delivery
                    if (it.data?.basket?.isEmpty() == true) setFragmentResultEmptyCart()
                }
                is States.Loading -> loading(true)
                is States.Error -> showError(it.error)
            }
        }
        binding.buttonBack.setOnClickListener { setFragmentResultBackStack() }
        binding.checkout.setOnClickListener {
            setFragmentResultEmptyCart()
            setFragmentResultBackStack()
        }
    }

    private fun loading(isVisible: Boolean) = run { binding.loading.isVisible = isVisible }

    private fun setFragmentResultEmptyCart() {
        requireActivity().supportFragmentManager.setFragmentResult(
            RequestKeys.CLEAR_CART.key,
            Bundle()
        )
    }
}
