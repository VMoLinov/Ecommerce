package ru.test.ecommerce.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.test.ecommerce.R
import ru.test.ecommerce.databinding.BottomSheetBinding
import ru.test.ecommerce.mainadapter.viewBinding

class BottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet) {
    private val binding: BottomSheetBinding by viewBinding { BottomSheetBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonDiscard.setOnClickListener { dismiss() }
        binding.buttonApply.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                "Bottom", Bundle().apply {
                    putInt("Select", binding.brand.selectedItemPosition)
                })
            dismiss()
        }
        val brands = arrayOf(
            "all",
            "Samsung",
            "Apple",
            "Motorola"
        )
        binding.brand.adapter = ArrayAdapter(
            requireContext(), support_simple_spinner_dropdown_item, brands
        )
    }

    companion object {
        fun newInstance(positions: IntArray): BottomSheet {
            val bundle = Bundle().apply { putIntArray("Array", positions) }
            return BottomSheet().apply { arguments = bundle }
        }
    }
}
