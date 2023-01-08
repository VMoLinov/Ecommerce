package ru.test.repository.local

import androidx.annotation.StringRes

interface ResourceProvider {

    fun string(@StringRes id: Int): String

    fun getBrands(): List<String>

    fun getPrices(): List<String>

    fun getSizes(): List<String>
}
