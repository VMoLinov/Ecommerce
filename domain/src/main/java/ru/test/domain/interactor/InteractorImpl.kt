package ru.test.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.test.domain.cartscreen.CartInteractor
import ru.test.domain.cartscreen.model.Cart
import ru.test.domain.detailsscreen.DetailsInteractor
import ru.test.domain.detailsscreen.model.DeviceDetails
import ru.test.domain.mainscreen.MainInteractor
import ru.test.domain.mainscreen.model.MainListItem
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val mainInteractor: MainInteractor,
    private val detailsInteractor: DetailsInteractor,
    private val cartInteractor: CartInteractor
) : Interactor {

    override suspend fun getMainListData(): Flow<List<MainListItem>> = mainInteractor.getData()

    override suspend fun getDeviceDetails(): Flow<DeviceDetails> = detailsInteractor.getData()

    override suspend fun getCartDetails(): Flow<Cart> = cartInteractor.getCartDetails()

    override suspend fun deleteItemFromCart(id: Long): Cart = cartInteractor.deleteItemFromCart(id)

    override suspend fun plusPosition(id: Long): Cart = cartInteractor.plusPosition(id)

    override suspend fun minusPosition(id: Long): Cart = cartInteractor.minusPosition(id)

    override suspend fun getBrands(): List<String> = mainInteractor.getBrands()

    override suspend fun getPrices(): List<String> = mainInteractor.getPrices()

    override suspend fun getSizes(): List<String> = mainInteractor.getSizes()
}
