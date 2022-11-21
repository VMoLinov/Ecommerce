package ru.test.featuremain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.test.core.utils.Filter
import ru.test.core.interactor.Interactor
import ru.test.core.utils.NoFilter
import ru.test.model.model.main.MainListItem

class MainViewModel(private val interactor: Interactor) : ViewModel() {

    private val category = MutableStateFlow(NoFilter)
    private val filter = MutableStateFlow(intArrayOf())
    val data = MutableStateFlow<List<MainListItem>>(listOf())

    init {
        viewModelScope.launch {
            interactor.getMainListData().collect { data.value = it }
        }
    }

    fun filterCategory(id: Long) {
        viewModelScope.launch { category.emit(Filter("$id")) }
    }

    fun filterBottom(array: IntArray) {
        viewModelScope.launch { filter.emit(array) }
    }
}
