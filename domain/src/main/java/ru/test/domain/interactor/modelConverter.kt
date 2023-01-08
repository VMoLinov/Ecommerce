package ru.test.domain.interactor

import ru.test.domain.cartscreen.model.Basket
import ru.test.domain.cartscreen.model.Cart
import ru.test.domain.detailsscreen.model.*
import ru.test.domain.mainscreen.model.BestSeller
import ru.test.domain.mainscreen.model.HomeStore
import ru.test.repository.network.model.*
import java.text.NumberFormat
import java.util.*

fun HomeStoreDTO.toHomeStore() =
    HomeStore(id, is_new, title, subtitle, picture, is_buy)

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
    Cart(basket.map { it.toBasket() }, delivery, id, basket.basketDtoToTotal())

fun BasketDTO.toBasket() = Basket(id, images, price, title, 1)

fun List<BasketDTO>.basketDtoToTotal(): String {
    return sumOf { it.price }.toPrice(0)
}

fun List<Basket>.getTotal(): String {
    return sumOf { it.priceNum * it.quantity }.toPrice(2)
}

private fun combineSpecs(
    colors: List<String>,
    memories: List<String>
): List<DetailsListItem> {
    val list = mutableListOf<DetailsListItem>()
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
