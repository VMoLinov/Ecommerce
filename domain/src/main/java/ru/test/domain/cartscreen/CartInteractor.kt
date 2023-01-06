package ru.test.domain.cartscreen

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.test.domain.cartscreen.model.Cart
import ru.test.domain.interactor.getTotal
import ru.test.domain.interactor.toCart
import ru.test.network.NetworkSource
import javax.inject.Inject

class CartInteractor @Inject constructor(private val network: NetworkSource) {

    private var cart: Cart? = null

    suspend fun getCartDetails(): Flow<Cart> {
        return network.getCartDetails().map { it.toCart() }.apply {
            collect { cart = it }
        }
    }

    fun deleteItemFromCart(id: Long): Cart = with(cart!!) {
        basket = basket.filter { item -> item.id != id }
        returnWithTotal()
    }

    fun plusPosition(id: Long): Cart = with(cart!!) {
        basket.map { if (it.id == id) it.quantity++ }
        returnWithTotal()
    }

    fun minusPosition(id: Long): Cart = with(cart!!) {
        basket.map { if (it.id == id) if (--it.quantity == 0) return deleteItemFromCart(id) }
        returnWithTotal()
    }

    private fun Cart.returnWithTotal(): Cart {
        total = basket.getTotal()
        return copy()
    }
}
