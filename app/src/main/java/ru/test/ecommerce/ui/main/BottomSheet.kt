package ru.test.ecommerce.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.test.core.viewBinding
import ru.test.ecommerce.App
import ru.test.ecommerce.R
import ru.test.ecommerce.databinding.BottomSheetBinding

class BottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet) {

    private val binding: BottomSheetBinding by viewBinding { BottomSheetBinding.bind(it) }
    private val viewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            App.getComponent().viewModel()
        )[BottomSheetViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonClear.setOnClickListener {
            updateSelectionOrClear()
            dismiss()
        }
        binding.buttonDone.setOnClickListener {
            updateSelectionOrClear(getPositionsFromSelectors())
            dismiss()
        }
    }

    private fun updateSelectionOrClear(array: IntArray = intArrayOf(0, 0, 0)) {
        setFragmentResult(array)
        viewModel.setPositions(array)
    }

    override fun onResume() {
        super.onResume()
        setSources()
    }

    private fun setSources() {
        adapterSet(viewModel.brands, binding.brand)
        adapterSet(viewModel.prices, binding.price)
        adapterSet(viewModel.sizes, binding.size)
        setSelected()
    }

    private fun setSelected() {
        lifecycleScope.launch {
            viewModel.selected.collectLatest {
                val iterator = it.iterator()
                binding.brand.setSelection(iterator.nextInt())
                binding.price.setSelection(iterator.nextInt())
                binding.size.setSelection(iterator.nextInt())
            }
        }
    }

    private fun adapterSet(source: MutableStateFlow<List<String>>, spinner: Spinner) {
        lifecycleScope.launch {
            source.collectLatest {
                spinner.adapter =
                    ArrayAdapter(requireContext(), support_simple_spinner_dropdown_item, it)
            }
        }
    }

    private fun setFragmentResult(arrayPositions: IntArray) {
        parentFragmentManager.setFragmentResult(
            MainFragment.REQUEST_FILTER, Bundle().apply {
                putIntArray(MainFragment.FILTER_POSITIONS, arrayPositions)
            })
    }

    private fun getPositionsFromSelectors(): IntArray = with(binding) {
        return intArrayOf(
            brand.selectedItemPosition,
            price.selectedItemPosition,
            size.selectedItemPosition
        )
    }
}
