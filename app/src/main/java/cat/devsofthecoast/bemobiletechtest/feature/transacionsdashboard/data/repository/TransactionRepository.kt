package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.repository

import cat.devsofthecoast.bemobiletechtest.common.data.remote.RepositoryResponse
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.model.ApiTransaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction

interface TransactionRepository {

    suspend fun getTransactions(): RepositoryResponse<List<Transaction>>

    suspend fun getConverionRates(): RepositoryResponse<List<ApiConversionRate>>

}