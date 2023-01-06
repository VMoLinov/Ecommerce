package ru.test.featurecart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.test.domain.cartscreen.model.Cart
import ru.test.domain.interactor.Interactor

class CartViewModel(private val interactor: Interactor) : ViewModel() {

    val data = MutableStateFlow<Cart?>(null)

    init {
        viewModelScope.launch {
            interactor.getCartDetails().collectLatest { data.value = it }
        }
    }

    fun minus(id: Long) {
        viewModelScope.launch {
            data.emit(interactor.minusPosition(id))
        }
    }

    fun plus(id: Long) {
        viewModelScope.launch {
            data.emit(interactor.plusPosition(id))
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch {
            data.emit(interactor.deleteItemFromCart(id))
        }
    }
}
