package ru.test.featuredetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.test.core.ui.States
import ru.test.domain.detailsscreen.model.DeviceDetails
import ru.test.domain.interactor.Interactor

class DetailsViewModel(private val interactor: Interactor) : ViewModel() {

    val details = MutableStateFlow<States<DeviceDetails>>(States.Loading)

    init {
        viewModelScope.launch {
            details.emit(States.Loading)
            try {
                interactor.getDeviceDetails().collect {
                    details.emit(States.Success(it))
                }
            } catch (e: Exception) {
                details.emit(States.Error(e))
            }
        }
    }
}
