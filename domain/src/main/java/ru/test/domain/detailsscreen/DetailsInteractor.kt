package ru.test.domain.detailsscreen

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.test.domain.detailsscreen.model.DeviceDetails
import ru.test.domain.interactor.toDeviceDetails
import ru.test.network.NetworkSource
import javax.inject.Inject

class DetailsInteractor @Inject constructor(private val network: NetworkSource) {

    suspend fun getData(): Flow<DeviceDetails> =
        network.getDeviceDetails().map { it.toDeviceDetails() }
}
