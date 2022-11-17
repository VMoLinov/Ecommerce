package ru.test.ecommerce.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.test.ecommerce.interactor.Interactor

class BottomSheetViewModel(private val interactor: Interactor) : ViewModel() {

    val selected = MutableStateFlow(intArrayOf(0, 0, 0))
    val brands = MutableStateFlow(listOf<String>())
    val prices = MutableStateFlow(listOf<String>())
    val sizes = MutableStateFlow(listOf<String>())

    init {
        viewModelScope.launch {
            brands.emit(interactor.getBrands())
            prices.emit(interactor.getPrices())
            sizes.emit(interactor.getSizes())
        }
    }

    fun setPositions(array: IntArray) {
        viewModelScope.launch {
            selected.emit(array)
        }
    }
}
