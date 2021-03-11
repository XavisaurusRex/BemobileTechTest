package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.repository.impl

import cat.devsofthecoast.bemobiletechtest.common.data.remote.CacheableRemoteResponse
import cat.devsofthecoast.bemobiletechtest.common.data.remote.RepositoryResponse
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local.TransactionsLocalDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.remote.TransactionsRemoteDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.repository.TransactionRepository
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val local: TransactionsLocalDataSource,
    private val remote: TransactionsRemoteDataSource
) : TransactionRepository {

    override suspend fun getTransactions(): RepositoryResponse<List<Transaction>> {
        return object : CacheableRemoteResponse<List<Transaction>>() {
            override suspend fun loadFromLocal(): List<Transaction>? {
                return local.getTransactionList()
            }

            override fun shouldRequestFromRemote(localResponse: List<Transaction>): Boolean {
                return localResponse.isEmpty()
            }

            override suspend fun requestRemoteCall(): List<Transaction> {
                return remote.getTransactions()
            }

            override suspend fun saveRemoteResponse(remoteResponse: List<Transaction>) {
                local.saveTransactionList(remoteResponse)
            }
        }.build()
    }

    override suspend fun getConverionRates(): RepositoryResponse<List<ApiConversionRate>> {
        return object : CacheableRemoteResponse<List<ApiConversionRate>>() {
            override suspend fun loadFromLocal(): List<ApiConversionRate>? {
                // TODO IMPLEMENT LOCAL DATASOURCE
                return emptyList()
            }

            override fun shouldRequestFromRemote(localResponse: List<ApiConversionRate>): Boolean {
                // TODO IMPLEMENT LOCAL DATASOURCE
                return true
            }

            override suspend fun requestRemoteCall(): List<ApiConversionRate> {
                return remote.getConversionRates()
            }

            override suspend fun saveRemoteResponse(remoteResponse: List<ApiConversionRate>) {
//                local.saveGames(remoteResponse)
            }
        }.build()
    }
}