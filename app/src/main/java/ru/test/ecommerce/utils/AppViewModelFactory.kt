package ru.test.ecommerce.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.test.ecommerce.interactor.Interactor
import javax.inject.Inject

class AppViewModelFactory @Inject constructor(
    private val interactor: Interactor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Interactor::class.java).newInstance(interactor)
    }
}
