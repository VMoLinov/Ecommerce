package ru.test.ecommerce.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.test.ecommerce.interactor.Interactor
import javax.inject.Inject

class BottomSheetViewModel (private val interactor: Interactor) : ViewModel() {

    val data = MutableStateFlow(Pair(0, 0))

    init {
    }
}
