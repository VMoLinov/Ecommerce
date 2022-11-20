package ru.test.ecommerce.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.test.ecommerce.interactor.local.ResourceProvider
import ru.test.network.NetworkSource
import ru.test.ecommerce.ui.cart.Cart
import ru.test.ecommerce.ui.details.DeviceDetails
import ru.test.ecommerce.ui.main.adapter.MainListItem
import ru.test.ecommerce.utils.*
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val local: ResourceProvider,
    private val network: NetworkSource
) : Interactor {

    private var cart: Cart? = null

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

    override suspend fun getDeviceDetails(): Flow<DeviceDetails> =
        network.getDeviceDetails().map { it.toDeviceDetails() }

    override suspend fun getCartDetails(): Flow<Cart> {
        return network.getCartDetails().map { it.toCart() }.apply {
            collect { cart = it }
        }
    }

    override suspend fun deleteItemFromCart(id: Long): Cart = with(cart!!) {
        basket = basket.filter { item -> item.id != id }
        returnWithTotal()
    }

    override suspend fun plusPosition(id: Long): Cart = with(cart!!) {
        basket.map { if (it.id == id) it.quantity++ }
        returnWithTotal()
    }

    override suspend fun minusPosition(id: Long): Cart = with(cart!!) {
        basket.map { if (it.id == id) if (--it.quantity == 0) return deleteItemFromCart(id) }
        returnWithTotal()
    }

    private fun Cart.returnWithTotal(): Cart {
        total = basket.getTotal()
        return copy()
    }

    override suspend fun getBrands(): List<String> = local.getBrands()

    override suspend fun getPrices(): List<String> = local.getPrices()

    override suspend fun getSizes(): List<String> = local.getSizes()
}
