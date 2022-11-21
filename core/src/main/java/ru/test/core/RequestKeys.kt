package ru.test.core

enum class RequestKeys(val key: String) {
    OPEN_DETAILS("openDetails"),
    ADD_TO_CART("addToCart"),
    CLEAR_CART("checkout"),
    BACK_STACK("backStack")
}
