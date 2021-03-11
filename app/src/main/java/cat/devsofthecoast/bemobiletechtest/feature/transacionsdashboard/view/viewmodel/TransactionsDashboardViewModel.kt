package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.devsofthecoast.bemobiletechtest.common.data.remote.AsyncResult
import cat.devsofthecoast.bemobiletechtest.common.domain.AppDispatchers
import cat.devsofthecoast.bemobiletechtest.common.domain.MutableSourceLiveData
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.usecase.RequestTransactionsToEurUseCase
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsDashboardViewModel @Inject constructor(
    private val requestTransactionsToEurUseCase: RequestTransactionsToEurUseCase,
    private val dispatchers: AppDispatchers,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _apiTransactions =
        MutableSourceLiveData<AsyncResult<List<TransactionDataWrapper>>>()
    val apiTransaction: LiveData<AsyncResult<List<TransactionDataWrapper>>> get() = _apiTransactions.liveData()

    fun requestMovements() = viewModelScope.launch(dispatchers.io) {
        _apiTransactions.changeSource(requestTransactionsToEurUseCase.execute())
    }

}