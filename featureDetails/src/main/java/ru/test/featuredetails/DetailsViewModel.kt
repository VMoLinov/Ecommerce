package ru.test.featuredetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.test.domain.detailsscreen.model.DeviceDetails
import ru.test.domain.interactor.Interactor

class DetailsViewModel(private val interactor: Interactor) : ViewModel() {

    val images = MutableStateFlow<DeviceDetails?>(null)

    init {
        viewModelScope.launch {
            interactor.getDeviceDetails().collect { images.value = it }
        }
    }
}
