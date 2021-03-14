package cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.data.repository

import cat.devsofthecoast.bemobiletechtest.common.data.remote.RepositoryResponse
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.dw.TransactionDetailsDataWrapper

interface TransactionDetailsRepository {

    suspend fun getTransactionsDetails(skuRefCode: String): RepositoryResponse<List<TransactionDetailsDataWrapper>>

}