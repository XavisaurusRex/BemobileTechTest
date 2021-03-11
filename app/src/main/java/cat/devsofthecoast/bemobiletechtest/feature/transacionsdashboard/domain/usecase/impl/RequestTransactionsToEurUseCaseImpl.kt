package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.usecase.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import cat.devsofthecoast.bemobiletechtest.common.data.remote.AsyncResult
import cat.devsofthecoast.bemobiletechtest.common.data.remote.error.AsyncError
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.CollectAndCalculateTransactionsMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.repository.TransactionRepository
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.usecase.RequestTransactionsToEurUseCase
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class RequestTransactionsToEurUseCaseImpl @Inject constructor(
    private val repository: TransactionRepository,
    private val collectAndCalculateTransactionsMapper: CollectAndCalculateTransactionsMapper
) : RequestTransactionsToEurUseCase {

    var forceRemoteRequests = false

    override fun setForceRemoteRequest(forceRemoteRequests: Boolean) {
        this.forceRemoteRequests = forceRemoteRequests
    }

    @InternalCoroutinesApi
    override suspend fun execute(): LiveData<AsyncResult<List<TransactionDataWrapper>>> {
        return repository.getConverionRates(forceRemoteRequests).flow()
            .combine(
                repository.getTransactions(forceRemoteRequests).flow()
            ) { resultConversionRates,
                resultTransactions ->

                var result: AsyncResult<List<TransactionDataWrapper>> = AsyncResult.loading()

                isAnyError(resultConversionRates, resultTransactions)?.let {
                    result = AsyncResult.error(it)
                }

                areSuccess(
                    resultConversionRates,
                    resultTransactions
                ) { rates: ConversionRates, transactions: List<Transaction> ->
                    result = AsyncResult.success(
                        collectAndCalculateTransactionsMapper
                            .mapTo(rates to transactions)
                    )
                }

                result
            }.catch {
                AsyncResult.error<List<TransactionDataWrapper>>(
                    AsyncError.UnknownError(
                        "Error RequestTransactionsToEurUseCase",
                        it
                    )
                )
            }.asLiveData(coroutineContext)
    }

    private fun areSuccess(
        arConversionRates: AsyncResult<ConversionRates>,
        arTransactions: AsyncResult<List<Transaction>>,
        function: (ConversionRates, List<Transaction>) -> Unit
    ) {
        if (arConversionRates.status == AsyncResult.Status.SUCCESS && arTransactions.status == AsyncResult.Status.SUCCESS) {
            arConversionRates.data?.let { apiConversions ->
                arTransactions.data?.let { apiTransactions ->
                    function(apiConversions, apiTransactions)
                }
            }
        }
    }

    private fun isAnyError(
        conversionRates: AsyncResult<ConversionRates>,
        transactions: AsyncResult<List<Transaction>>
    ): AsyncError? {
        return when {
            conversionRates.status == AsyncResult.Status.ERROR -> conversionRates.error
            transactions.status == AsyncResult.Status.ERROR -> transactions.error
            else -> null
        }
    }

}
