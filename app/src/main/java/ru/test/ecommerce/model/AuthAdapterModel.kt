package ru.test.ecommerce.model

import ru.test.ecommerce.adapter.DelegateAdapterItem

data class AuthAdapterModel(
    val phone: String = "+7"
) : DelegateAdapterItem {

    override fun id(): Any = AuthAdapterModel::class.toString()

    override fun content(): Any = phone
}
