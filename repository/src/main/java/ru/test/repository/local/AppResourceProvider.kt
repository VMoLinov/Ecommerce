package ru.test.repository.local

import android.content.Context
import ru.test.repository.R
import javax.inject.Inject

class AppResourceProvider @Inject constructor(
    private val context: Context
) : ResourceProvider {

    override fun string(id: Int): String = context.resources.getString(id)
    private fun stringArray(id: Int): List<String> = context.resources.getStringArray(id).toList()

    override fun getBrands(): List<String> = stringArray(R.array.brands)

    override fun getPrices(): List<String> = stringArray(R.array.prices)

    override fun getSizes(): List<String> = stringArray(R.array.sizes)
}
