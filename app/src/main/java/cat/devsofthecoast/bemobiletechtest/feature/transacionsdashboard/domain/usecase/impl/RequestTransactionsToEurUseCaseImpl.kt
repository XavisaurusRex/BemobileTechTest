package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.usecase.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import cat.devsofthecoast.bemobiletechtest.common.data.remote.AsyncResult
import cat.devsofthecoast.bemobiletechtest.common.data.remote.error.AsyncError
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.CollectAndCalculateTransactionsMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiTransaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.repository.TransactionRepository
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

    @InternalCoroutinesApi
    override suspend fun execute(): LiveData<AsyncResult<List<TransactionDataWrapper>>> {
        return repository.getConverionRates().flow()
            .combine(
                repository.getTransactions().flow()
            ) { resultConversionRates,
                resultTransactions ->

                var result: AsyncResult<List<TransactionDataWrapper>> = AsyncResult.loading()

                // TODO: 3/7/21 IMPROVE CONDITIONS

                isAnyError(resultConversionRates, resultTransactions)?.let {
                    result = AsyncResult.error(it)
                }

                areSuccess(
                    resultConversionRates,
                    resultTransactions
                ) { list: List<ApiConversionRate>, list1: List<ApiTransaction> ->
                    result = AsyncResult.success(
                        collectAndCalculateTransactionsMapper
                            .mapToBo(list to list1)
                            .map { TransactionDataWrapper(it) }
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
        arConversionRates: AsyncResult<List<ApiConversionRate>>,
        arTransactions: AsyncResult<List<ApiTransaction>>,
        function: (conversionRates: List<ApiConversionRate>, transactions: List<ApiTransaction>) -> Unit
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
        conversionRates: AsyncResult<List<ApiConversionRate>>,
        transactions: AsyncResult<List<ApiTransaction>>
    ): AsyncError? {
        return when {
            conversionRates.status == AsyncResult.Status.ERROR -> conversionRates.error
            transactions.status == AsyncResult.Status.ERROR -> transactions.error
            else -> null
        }
    }

}
