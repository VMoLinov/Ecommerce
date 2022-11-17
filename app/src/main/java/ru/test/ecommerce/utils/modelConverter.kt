package ru.test.ecommerce.utils

import ru.test.ecommerce.interactor.local.model.BestSeller
import ru.test.ecommerce.interactor.local.model.HomeStore
import ru.test.ecommerce.interactor.network.model.BestSellerDTO
import ru.test.ecommerce.interactor.network.model.DeviceDetailsDTO
import ru.test.ecommerce.interactor.network.model.HomeStoreDTO
import ru.test.ecommerce.ui.details.DeviceDetails
import ru.test.ecommerce.ui.details.adapter.DetailsListItem
import ru.test.ecommerce.ui.details.adapter.items.ColorItem
import ru.test.ecommerce.ui.details.adapter.items.ImageItem
import ru.test.ecommerce.ui.details.adapter.items.MemoryItem
import java.text.NumberFormat
import java.util.*

fun HomeStoreDTO.toHomeStore() = HomeStore(id, is_new, title, subtitle, picture, is_buy)

fun BestSellerDTO.toBestSeller() =
    BestSeller(
        id,
        is_favorites,
        title,
        price_without_discount.toPrice(0),
        discount_price.toPrice(0),
        picture
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

private fun combineSpecs(colors: List<String>, memories: List<String>): List<DetailsListItem> {
    val list = mutableListOf<DetailsListItem>()
    colors.forEach { list.add(ColorItem(it)) }
    memories.forEach { list.add(MemoryItem(it)) }
    return list
}

private fun Number.toPrice(fraction: Int): String {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = fraction
    format.currency = Currency.getInstance(Locale.getDefault())
    return format.format(this)
}
