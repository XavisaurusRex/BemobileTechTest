package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.repository.impl

import cat.devsofthecoast.bemobiletechtest.common.data.remote.CacheableRemoteResponse
import cat.devsofthecoast.bemobiletechtest.common.data.remote.RepositoryResponse
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.remote.TransactionsRemoteDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiTransaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.repository.TransactionRepository
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val remote: TransactionsRemoteDataSource
) : TransactionRepository {

    override suspend fun getTransactions(): RepositoryResponse<List<ApiTransaction>> {
        return object : CacheableRemoteResponse<List<ApiTransaction>>() {
            override suspend fun loadFromLocal(): List<ApiTransaction>? {
                // TODO IMPLEMENT LOCAL DATASOURCE
//                return local.getGames()
                return emptyList()
            }

            override fun shouldRequestFromRemote(localResponse: List<ApiTransaction>): Boolean {
                // TODO IMPLEMENT LOCAL DATASOURCE
                return true
            }

            override suspend fun requestRemoteCall(): List<ApiTransaction> {
                return remote.getTransactions()
            }

            override suspend fun saveRemoteResponse(remoteResponse: List<ApiTransaction>) {
                // TODO IMPLEMENT LOCAL DATASOURCE
//                local.saveGames(remoteResponse)
            }
        }.build()
    }

    override suspend fun getConverionRates(): RepositoryResponse<List<ApiConversionRate>> {
        return object : CacheableRemoteResponse<List<ApiConversionRate>>() {
            override suspend fun loadFromLocal(): List<ApiConversionRate>? {
                // TODO IMPLEMENT LOCAL DATASOURCE
//                return local.getGames()
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
                // TODO IMPLEMENT LOCAL DATASOURCE
//                local.saveGames(remoteResponse)
            }
        }.build()    }
}