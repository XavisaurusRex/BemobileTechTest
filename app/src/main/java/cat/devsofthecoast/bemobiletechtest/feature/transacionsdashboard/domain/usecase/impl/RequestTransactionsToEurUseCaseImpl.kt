package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.usecase.impl

import cat.devsofthecoast.bemobiletechtest.common.data.remote.AsyncResult
import cat.devsofthecoast.bemobiletechtest.common.data.remote.error.AsyncError
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.CollectAndCalculateTransactionsMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.repository.TransactionRepository
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.usecase.RequestTransactionsToEurUseCase
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class RequestTransactionsToEurUseCaseImpl @Inject constructor(
    private val repository: TransactionRepository,
    private val collectAndCalculateTransactionsMapper: CollectAndCalculateTransactionsMapper
) : RequestTransactionsToEurUseCase {

    var forceRemoteRequests = false

    override fun setForceRemoteRequest(forceRemoteRequests: Boolean) {
        this.forceRemoteRequests = forceRemoteRequests
    }

    @InternalCoroutinesApi
    override suspend fun execute(): Flow<AsyncResult<List<TransactionDataWrapper>>> {
        return repository.getConverionRates(forceRemoteRequests).flow()
            .combine(
                repository.getTransactions(forceRemoteRequests).flow()
            ) { resultConversionRates,
                resultTransactions ->

                if (resultConversionRates.status == AsyncResult.Status.SUCCESS &&
                    resultTransactions.status == AsyncResult.Status.SUCCESS
                ) {
                    AsyncResult.success(
                        collectAndCalculateTransactionsMapper
                            .mapTo(resultConversionRates.data!! to resultTransactions.data!!)
                    )
                } else if (resultConversionRates.status == AsyncResult.Status.ERROR) {
                    AsyncResult.error(resultConversionRates.error!!)
                } else if (resultTransactions.status == AsyncResult.Status.ERROR) {
                    AsyncResult.error(resultTransactions.error!!)
                } else {
                    AsyncResult.loading()
                }

            }.catch {
                AsyncResult.error<List<TransactionDataWrapper>>(
                    AsyncError.UnknownError(errorThrowed = it)
                )
            }
    }
}
