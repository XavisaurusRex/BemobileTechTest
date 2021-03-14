package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import cat.devsofthecoast.bemobiletechtest.common.data.remote.AsyncResult
import cat.devsofthecoast.bemobiletechtest.common.domain.AppDispatchers
import cat.devsofthecoast.bemobiletechtest.common.view.viewmodel.BaseViewModel
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.usecase.RequestTransactionsToEurUseCase
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.fragment.TransactionsDashboardFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsDashboardViewModel @Inject constructor(
    private val requestTransactionsToEurUseCase: RequestTransactionsToEurUseCase,
    private val dispatchers: AppDispatchers,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle) {

    private var _transactions: MutableLiveData<List<TransactionDataWrapper>?> =
        savedStateHandle.getLiveData("_transactions")


    val transactions: LiveData<List<TransactionDataWrapper>?>
        get() = _transactions

    init {
        if (savedStateHandle.contains("_transactions").not()) {
            requestMovements()
        }
    }

    fun requestMovements(forceRemoteRequests: Boolean = false) =
        viewModelScope.launch(dispatchers.io) {
            requestTransactionsToEurUseCase.setForceRemoteRequest(forceRemoteRequests)
            requestTransactionsToEurUseCase.execute()
                .collectLatest {
                    _transactions.postValue(it.data)
                    _loading.postValue(it.status == AsyncResult.Status.LOADING)
                    _error.postValue(it.status == AsyncResult.Status.ERROR)
                }
        }

    fun goToTransactionDetails(transactionDetails: TransactionDetails) {
        navigate(
            TransactionsDashboardFragmentDirections.actionFromDashboardToDetails(
                transactionDetails
            )
        )
    }
}