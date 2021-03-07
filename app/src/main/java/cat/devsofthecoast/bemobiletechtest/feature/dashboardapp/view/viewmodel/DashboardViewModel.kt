package cat.devsofthecoast.bemobiletechtest.feature.dashboardapp.view.viewmodel

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movements: MutableLiveData<Movements> = savedStateHandle.getLiveData("movements")
    val movements get() = _movements

    init {
        requestMovements()
    }

    fun requestMovements() {
        if (_movements.value.isNullOrEmpty()) {
            Log.d("BemobileApp", "movements.value.isNullOrEmpty()")
            _movements.postValue(
                arrayListOf(
                    Movement(
                        "Movimiento 1 - valor 0"
                    ),
                    Movement(
                        "Movimiento 2 - valor 1"
                    ),
                    Movement(
                        "Movimiento 3 - valor 2"
                    )
                )
            )
        }
    }

}