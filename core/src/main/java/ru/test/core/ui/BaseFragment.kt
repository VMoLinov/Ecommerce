package ru.test.core.ui

import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.Flow
import ru.test.core.launchAndRepeatOnStart

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    fun <T> Flow<T>.collectWhileStarted(block: (T) -> Unit) {
        launchAndRepeatOnStart {
            collect { block.invoke(it) }
        }
    }
}
