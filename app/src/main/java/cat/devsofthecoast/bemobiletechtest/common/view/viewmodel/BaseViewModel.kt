package cat.devsofthecoast.bemobiletechtest.common.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import cat.devsofthecoast.bemobiletechtest.common.view.navigation.HandledEvent
import cat.devsofthecoast.bemobiletechtest.common.view.navigation.NavigationOrder

abstract class BaseViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _navigation = MutableLiveData<HandledEvent<NavigationOrder>>()
    val navigation: LiveData<HandledEvent<NavigationOrder>> get() = _navigation

    protected var _loading: MutableLiveData<Boolean> = savedStateHandle.getLiveData("_loading")
    val loading: LiveData<Boolean> get() = _loading

    protected var _error: MutableLiveData<Boolean> = savedStateHandle.getLiveData("_error")
    val error: LiveData<Boolean> get() = _error

    fun navigate(directions: NavDirections) {
        _navigation.value = HandledEvent(NavigationOrder.To(directions))
    }

    fun navigateBack() {
        _navigation.value = HandledEvent(NavigationOrder.Back)
    }
}
