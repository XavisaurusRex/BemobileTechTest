package cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import cat.devsofthecoast.bemobiletechtest.common.data.remote.AsyncResult
import cat.devsofthecoast.bemobiletechtest.common.domain.AppDispatchers
import cat.devsofthecoast.bemobiletechtest.common.view.viewmodel.BaseViewModel
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.domain.usecase.GetTransactionsDetailsUseCase
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.dw.TransactionDetailsDataWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionDetailsViewModel @Inject constructor(
    private val getTransactionsDetailsUseCase: GetTransactionsDetailsUseCase,
    private val dispatchers: AppDispatchers,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle) {

    private var _transactionDetails: MutableLiveData<List<TransactionDetailsDataWrapper>> =
        savedStateHandle.getLiveData("_transactions")

    val transactionDetails: LiveData<List<TransactionDetailsDataWrapper>>
        get() = _transactionDetails

    fun setRequestParams(skuRefCode: String) {
        if (savedStateHandle.contains("_transactions").not()) {
            requestMovementDetails(skuRefCode)
        }
    }

    private fun requestMovementDetails(skuRefCode: String) =
        viewModelScope.launch(dispatchers.io) {
            getTransactionsDetailsUseCase.setSkuRefCode(skuRefCode)
            getTransactionsDetailsUseCase.execute()
                .collectLatest {
                    _transactionDetails.postValue(it.data)
                    _loading.postValue(it.status == AsyncResult.Status.LOADING)
                    _error.postValue(it.status == AsyncResult.Status.ERROR)
                }
        }

}