package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.usecase.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import cat.devsofthecoast.bemobiletechtest.common.data.remote.AsyncResult
import cat.devsofthecoast.bemobiletechtest.common.data.remote.error.AsyncError
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiTransaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.repository.TransactionRepository
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Currency
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.usecase.RequestTransactionsToEurUseCase
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class RequestTransactionsToEurUseCaseImpl @Inject constructor(
    private val repository: TransactionRepository
) : RequestTransactionsToEurUseCase {

    @InternalCoroutinesApi
    override suspend fun execute(): LiveData<AsyncResult<List<TransactionDataWrapper>>> {
        return repository.getConverionRates().flow()
            .combine(
                repository.getTransactions().flow()
            ) { conversionRates,
                transactions ->

                var result: AsyncResult<List<TransactionDataWrapper>> = AsyncResult.loading()

                // TODO: 3/7/21 IMPROVE CONDITIONS

                isAnyError(conversionRates, transactions)?.let {
                    result = AsyncResult.error(it)
                }

                areSuccess(
                    conversionRates,
                    transactions
                ) { list: List<ApiConversionRate>, list1: List<ApiTransaction> ->
                    result = calculateTransactionConversionRates(list, list1)
                }

                result
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

    private fun calculateTransactionConversionRates(
        list: List<ApiConversionRate>,
        list1: List<ApiTransaction>
    ): AsyncResult<List<TransactionDataWrapper>> {
        // TODO: 3/7/21 LA FUNCION TOCHISSIMA QUE DESFRAGMENTAR EN OTRA CLASSE
        val transactionDataWrappers = arrayListOf<TransactionDataWrapper>()
        list1.forEach { apiTransaction ->

            transactionDataWrappers.add(
                TransactionDataWrapper(
                    TransactionDetails(
                        "${apiTransaction.skuStockRef} ${apiTransaction.amount} ${apiTransaction.currency}",
                        0.0,
                        Currency.USD,
                        0.0,
                        Currency.USD,
                        1.0
                    )
                )
            )
        }

        return AsyncResult.success(transactionDataWrappers)
    }
}
