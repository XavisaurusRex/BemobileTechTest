package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.repository

import cat.devsofthecoast.bemobiletechtest.common.data.remote.RepositoryResponse
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction

interface TransactionRepository {

    suspend fun getTransactions(forceRemoteRequests: Boolean = false): RepositoryResponse<List<Transaction>>

    suspend fun getConverionRates(forceRemoteRequests: Boolean = false): RepositoryResponse<ConversionRates>

}