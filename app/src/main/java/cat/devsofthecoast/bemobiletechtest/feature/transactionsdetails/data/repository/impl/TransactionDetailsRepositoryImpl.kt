package cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.data.repository.impl

import cat.devsofthecoast.bemobiletechtest.common.data.remote.CacheableRemoteResponse
import cat.devsofthecoast.bemobiletechtest.common.data.remote.RepositoryResponse
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local.TransactionsLocalDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.data.repository.TransactionDetailsRepository
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.domain.mapper.TransactionDetailsMapper
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.dw.TransactionDetailsDataWrapper
import javax.inject.Inject

class TransactionDetailsRepositoryImpl @Inject constructor(
    private val local: TransactionsLocalDataSource,
    private val mapper: TransactionDetailsMapper
) : TransactionDetailsRepository {

    override suspend fun getTransactionsDetails(skuRefCode: String): RepositoryResponse<List<TransactionDetailsDataWrapper>> {
        return object : CacheableRemoteResponse<List<TransactionDetailsDataWrapper>>() {
            override suspend fun loadFromLocal(): List<TransactionDetailsDataWrapper> {
                return mapper.mapTo(local.getTransactionDetailsForProduct(skuRefCode))
            }

            override fun shouldRequestFromRemote(localResponse: List<TransactionDetailsDataWrapper>?): Boolean {
                return false
            }

            override suspend fun requestRemoteCall(): List<TransactionDetailsDataWrapper> {
                return listOf()
            }

            override suspend fun saveRemoteResponse(remoteResponse: List<TransactionDetailsDataWrapper>) {
                // no op
            }
        }.build()
    }
}