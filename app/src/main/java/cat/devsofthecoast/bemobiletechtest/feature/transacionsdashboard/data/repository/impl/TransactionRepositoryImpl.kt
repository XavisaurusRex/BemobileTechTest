package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.repository.impl

import cat.devsofthecoast.bemobiletechtest.common.data.remote.CacheableRemoteResponse
import cat.devsofthecoast.bemobiletechtest.common.data.remote.RepositoryResponse
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local.ConversionRatesLocalDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local.TransactionsLocalDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.remote.TransactionsRemoteDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.repository.TransactionRepository
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionsLocal: TransactionsLocalDataSource,
    private val conversionRatesLocal: ConversionRatesLocalDataSource,
    private val remote: TransactionsRemoteDataSource
) : TransactionRepository {

    override suspend fun getTransactions(forceRemoteRequests: Boolean): RepositoryResponse<List<Transaction>> {
        return object : CacheableRemoteResponse<List<Transaction>>(forceRemoteRequests) {
            override suspend fun loadFromLocal(): List<Transaction>? {
                return transactionsLocal.getTransactionList()
            }

            override fun shouldRequestFromRemote(localResponse: List<Transaction>?): Boolean {
                return forceRemoteRequests || localResponse.isNullOrEmpty()
            }

            override suspend fun requestRemoteCall(): List<Transaction> {
                return remote.getTransactions()
            }

            override suspend fun saveRemoteResponse(remoteResponse: List<Transaction>) {
                transactionsLocal.saveTransactionList(remoteResponse)
            }
        }.build()
    }

    override suspend fun getConverionRates(forceRemoteRequests: Boolean): RepositoryResponse<ConversionRates> {
        return object : CacheableRemoteResponse<ConversionRates>(forceRemoteRequests) {
            override suspend fun loadFromLocal(): ConversionRates? {
                return conversionRatesLocal.getConversionRates()
            }

            override fun shouldRequestFromRemote(localResponse: ConversionRates?): Boolean {
                return forceRemoteRequests || localResponse == null || localResponse.keys.isNullOrEmpty()
            }

            override suspend fun requestRemoteCall(): ConversionRates {
                return remote.getConversionRates()
            }

            override suspend fun saveRemoteResponse(remoteResponse: ConversionRates) {
                conversionRatesLocal.saveConversionRates(remoteResponse)
            }
        }.build()
    }
}