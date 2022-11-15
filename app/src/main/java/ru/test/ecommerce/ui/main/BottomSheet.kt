package ru.test.ecommerce.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.test.ecommerce.R
import ru.test.ecommerce.mainadapter.viewBinding
import ru.test.ecommerce.databinding.BottomSheetBinding

class BottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet) {
    private val binding: BottomSheetBinding by viewBinding { BottomSheetBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonDiscard.setOnClickListener {
            dismiss()
        }
        binding.buttonApply.setOnClickListener {
            dismiss()
        }
        val courses = arrayOf(
            "C", "Data structures",
            "Interview prep", "Algorithms",
            "DSA with java", "OS"
        )
        binding.brand.adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            courses
        )
    }
}
