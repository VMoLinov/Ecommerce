package ru.test.featuredetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.test.core.interactor.Interactor
import ru.test.model.model.details.DeviceDetails

class DetailsViewModel(private val interactor: Interactor) : ViewModel() {

    val images = MutableStateFlow<DeviceDetails?>(null)

    init {
        viewModelScope.launch {
            interactor.getDeviceDetails().collect { images.value = it }
        }
    }
}
