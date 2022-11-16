package ru.test.ecommerce.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.test.ecommerce.interactor.Interactor
import ru.test.ecommerce.ui.main.adapter.MainListItem
import ru.test.ecommerce.utils.Filter
import ru.test.ecommerce.utils.NoFilter

class MainViewModel(private val interactor: Interactor) : ViewModel() {

    private val category = MutableStateFlow(NoFilter)
    private val filter = MutableStateFlow(Pair(0, 0))
    val data = MutableStateFlow<List<MainListItem>>(listOf())

    init {
        viewModelScope.launch {
            interactor.getMainListData().collect { data.value = it }
        }
    }

    fun filterCategory(name: String) {
        viewModelScope.launch { category.emit(Filter(name)) }
    }

    fun filterBottom(pair: Pair<Int, Int>) {
        viewModelScope.launch { filter.emit(pair) }
    }
}
