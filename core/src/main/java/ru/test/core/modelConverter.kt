package ru.test.core

import ru.test.model.ColorItem
import ru.test.model.ImageItem
import ru.test.model.MemoryItem
import ru.test.network.model.*
import java.text.NumberFormat
import java.util.*

fun HomeStoreDTO.toHomeStore() =
    ru.test.model.model.HomeStore(id, is_new, title, subtitle, picture, is_buy)

fun BestSellerDTO.toBestSeller() = ru.test.model.model.BestSeller(
    id = id,
    isFavorites = is_favorites,
    title = title,
    priceWithoutDiscount = price_without_discount.toPrice(0),
    discountPrice = discount_price.toPrice(0),
    picture = picture
)

fun DeviceDetailsDTO.toDeviceDetails() = ru.test.model.model.DeviceDetails(
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
    ru.test.model.model.Cart(basket.map { it.toBasket() }, delivery, id, basket.basketDtoToTotal())

fun BasketDTO.toBasket() = ru.test.model.model.Basket(id, images, price, title, 1)

fun List<BasketDTO>.basketDtoToTotal(): String {
    return sumOf { it.price }.toPrice(0)
}

fun List<ru.test.model.model.Basket>.getTotal(): String {
    return sumOf { it.priceNum * it.quantity }.toPrice(2)
}

private fun combineSpecs(
    colors: List<String>,
    memories: List<String>
): List<ru.test.model.model.DetailsListItem> {
    val list = mutableListOf<ru.test.model.model.DetailsListItem>()
    colors.forEach { list.add(ColorItem(it)) }
    memories.forEach { list.add(MemoryItem("$it GB")) }
    return list
}

fun Number.toPrice(fraction: Int): String {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = fraction
    format.currency = Currency.getInstance(Locale.getDefault())
    return format.format(this)
}
