package ru.test.ecommerce.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.test.ecommerce.repository.NetworkSource
import ru.test.ecommerce.repository.NetworkSourceImpl
import ru.test.ecommerce.repository.model.ResultDTO

class MainViewModel(private val repository: NetworkSource = NetworkSourceImpl()) : ViewModel() {

    private val filter = MutableStateFlow(NoFilter)
    val data = MutableStateFlow(ResultDTO(emptyList(), emptyList()))

    init {
        viewModelScope.launch {
            repository.getMainList().collect { data.value = it }
        }
    }

    fun filterCategory(name: String) {
        viewModelScope.launch { filter.emit(Filter(name)) }
    }
}

@JvmInline
value class Filter(val string: String)

val NoFilter = Filter("")
