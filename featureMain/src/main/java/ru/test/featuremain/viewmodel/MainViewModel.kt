package ru.test.featuremain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.test.core.ui.States
import ru.test.core.utils.Filter
import ru.test.core.utils.NoFilter
import ru.test.domain.interactor.Interactor
import ru.test.domain.mainscreen.model.MainListItem

class MainViewModel(private val interactor: Interactor) : ViewModel() {

    private val category = MutableStateFlow(NoFilter)
    private val filter = MutableStateFlow(intArrayOf())
    val data = MutableStateFlow<States<List<MainListItem>>>(States.Loading)

    init {
        viewModelScope.launch {
            data.emit(States.Loading)
            try {
                interactor.getMainListData().collect { data.emit(States.Success(it)) }
            } catch (e: Exception) {
                data.emit(States.Error(e))
            }
        }
    }

    fun filterCategory(id: Long) {
        viewModelScope.launch { category.emit(Filter("$id")) }
    }

    fun filterBottom(array: IntArray) {
        viewModelScope.launch { filter.emit(array) }
    }
}
