package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.devsofthecoast.bemobiletechtest.common.data.remote.AsyncResult
import cat.devsofthecoast.bemobiletechtest.common.domain.AppDispatchers
import cat.devsofthecoast.bemobiletechtest.common.domain.MutableSourceLiveData
import cat.devsofthecoast.bemobiletechtest.common.view.viewmodel.BaseViewModel
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.usecase.RequestTransactionsToEurUseCase
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.fragment.TransactionsDashboardFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsDashboardViewModel @Inject constructor(
    private val requestTransactionsToEurUseCase: RequestTransactionsToEurUseCase,
    private val dispatchers: AppDispatchers,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _apiTransactions =
        MutableSourceLiveData<AsyncResult<List<TransactionDataWrapper>>>()
    val apiTransaction: LiveData<AsyncResult<List<TransactionDataWrapper>>> get() = _apiTransactions.liveData()

    fun requestMovements(forceRemoteRequests: Boolean = false) = viewModelScope.launch(dispatchers.io) {
        requestTransactionsToEurUseCase.setForceRemoteRequest(forceRemoteRequests)
        _apiTransactions.changeSource(requestTransactionsToEurUseCase.execute())
    }

    fun goToTransactionDetails(transactionDetails: TransactionDetails){
        navigate(TransactionsDashboardFragmentDirections.actionFromDashboardToDetails(transactionDetails))
    }
}