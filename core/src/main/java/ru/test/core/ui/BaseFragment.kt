package ru.test.core.ui

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.Flow
import ru.test.core.utils.RequestKeys
import ru.test.core.utils.launchAndRepeatOnStart

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    fun <T> Flow<T>.collectWhileStarted(block: (T) -> Unit) {
        launchAndRepeatOnStart {
            collect { block.invoke(it) }
        }
    }

    fun setFragmentResultBackStack() {
        requireActivity().supportFragmentManager.setFragmentResult(
            RequestKeys.BACK_STACK.key,
            Bundle()
        )
    }

    fun showError(e: Exception) {
        Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
    }
}
