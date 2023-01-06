package ru.test.core.ui

sealed class States<out T> {
    class Success<out V>(val data: V) : States<V>()
    class Error(val error: Exception) : States<Nothing>()
    object Loading : States<Nothing>()
}
