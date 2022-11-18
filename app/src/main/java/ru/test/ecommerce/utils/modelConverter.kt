package ru.test.ecommerce.utils

import ru.test.ecommerce.interactor.local.model.BestSeller
import ru.test.ecommerce.interactor.local.model.HomeStore
import ru.test.ecommerce.interactor.network.model.*
import ru.test.ecommerce.ui.cart.Basket
import ru.test.ecommerce.ui.cart.Cart
import ru.test.ecommerce.ui.details.DeviceDetails
import ru.test.ecommerce.ui.details.adapter.DetailsListItem
import ru.test.ecommerce.ui.details.adapter.items.ColorItem
import ru.test.ecommerce.ui.details.adapter.items.ImageItem
import ru.test.ecommerce.ui.details.adapter.items.MemoryItem
import java.text.NumberFormat
import java.util.*

fun HomeStoreDTO.toHomeStore() = HomeStore(id, is_new, title, subtitle, picture, is_buy)

fun BestSellerDTO.toBestSeller() = BestSeller(
    id = id,
    isFavorites = is_favorites,
    title = title,
    priceWithoutDiscount = price_without_discount.toPrice(0),
    discountPrice = discount_price.toPrice(0),
    picture = picture
)

fun DeviceDetailsDTO.toDeviceDetails() = DeviceDetails(
    CPU = CPU,
    camera = camera,
    specs = combineSpecs(color, capacity),
    id = id,
    images = images.map { ImageItem(it) },
    isFavorites = isFavorites,
    price = price.toPrice(2),
    rating = rating,
    sd = sd,
    ssd = ssd,
    title = title
)

fun CartDTO.toCart() =
    Cart(basket.map { it.toBasket() }.toMutableList(), delivery, id, basket.basketDtoToTotal())

fun BasketDTO.toBasket() = Basket(id, images, price, title, 1)

fun List<BasketDTO>.basketDtoToTotal(): String {
    return sumOf { it.price }.toPrice(0)
}

fun List<Basket>.basketToTotal(): String {
    return sumOf { it.priceNum * it.quantity }.toPrice(2)
}

private fun combineSpecs(colors: List<String>, memories: List<String>): List<DetailsListItem> {
    val list = mutableListOf<DetailsListItem>()
    colors.forEach { list.add(ColorItem(it)) }
    memories.forEach { list.add(MemoryItem(it)) }
    return list
}

fun Number.toPrice(fraction: Int): String {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = fraction
    format.currency = Currency.getInstance(Locale.getDefault())
    return format.format(this)
}
