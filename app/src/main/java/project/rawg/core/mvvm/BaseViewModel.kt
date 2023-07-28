package project.rawg.core.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun launch(func: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(block = func)
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}
