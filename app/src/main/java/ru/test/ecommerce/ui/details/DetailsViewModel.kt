package ru.test.ecommerce.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.test.ecommerce.interactor.Interactor

class DetailsViewModel(private val interactor: Interactor) : ViewModel() {

    val images = MutableStateFlow<DeviceDetails?>(null)

    init {
        viewModelScope.launch {
            interactor.getDeviceDetails().collect { images.value = it }
        }
    }
}
