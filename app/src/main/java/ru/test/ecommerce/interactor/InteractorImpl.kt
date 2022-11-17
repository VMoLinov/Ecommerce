package ru.test.ecommerce.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.test.ecommerce.interactor.local.ResourceProvider
import ru.test.ecommerce.interactor.network.NetworkSource
import ru.test.ecommerce.ui.details.DeviceDetails
import ru.test.ecommerce.ui.main.adapter.MainListItem
import ru.test.ecommerce.utils.toBestSeller
import ru.test.ecommerce.utils.toDeviceDetails
import ru.test.ecommerce.utils.toHomeStore
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val local: ResourceProvider,
    private val network: NetworkSource
) : Interactor {

    override suspend fun getMainListData(): Flow<List<MainListItem>> {
        val data = network.getMainList()
        return flow {
            emit(
                local.wrapDataWithConstants(
                    data.first().home_store.map { it.toHomeStore() },
                    data.first().best_seller.map { it.toBestSeller() }
                )
            )
        }
    }

    override suspend fun getDeviceDetails(): Flow<DeviceDetails> = network.getDeviceDetails().map { it.toDeviceDetails() }

    override suspend fun getBrands(): List<String> = local.getBrands()

    override suspend fun getPrices(): List<String> = local.getPrices()

    override suspend fun getSizes(): List<String> = local.getSizes()
}
