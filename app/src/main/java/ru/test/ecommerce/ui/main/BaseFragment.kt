package ru.test.ecommerce.ui.main

import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.Flow
import ru.test.ecommerce.utils.launchAndRepeatOnStart

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    fun <T> Flow<T>.collectWhileStarted(block: (T) -> Unit) {
        launchAndRepeatOnStart {
            collect { block.invoke(it) }
        }
    }
}
