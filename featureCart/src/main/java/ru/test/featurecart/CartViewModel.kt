package ru.test.featurecart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.test.core.ui.States
import ru.test.domain.cartscreen.model.Cart
import ru.test.domain.interactor.Interactor

class CartViewModel(private val interactor: Interactor) : ViewModel() {

    val data = MutableStateFlow<States<Cart?>>(States.Success(null))

    init {
        viewModelScope.launch {
            data.emit(States.Loading)
            try {
                interactor.getCartDetails().collectLatest {
                    data.emit(States.Success(it))
                }
            } catch (e: Exception) {
                data.emit(States.Error(e))
            }
        }
    }

    fun minus(id: Long) {
        viewModelScope.launch {
            data.emit(States.Success(interactor.minusPosition(id)))
        }
    }

    fun plus(id: Long) {
        viewModelScope.launch {
            data.emit(States.Success(interactor.plusPosition(id)))
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch {
            data.emit(States.Success(interactor.deleteItemFromCart(id)))
        }
    }
}
