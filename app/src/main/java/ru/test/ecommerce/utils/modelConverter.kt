package ru.test.ecommerce.utils

import ru.test.ecommerce.interactor.local.model.BestSeller
import ru.test.ecommerce.interactor.local.model.HomeStore
import ru.test.ecommerce.interactor.network.model.BestSellerDTO
import ru.test.ecommerce.interactor.network.model.HomeStoreDTO
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

private fun Number.toPrice(fraction: Int): String {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = fraction
    format.currency = Currency.getInstance(Locale.getDefault())
    return format.format(this)
}
