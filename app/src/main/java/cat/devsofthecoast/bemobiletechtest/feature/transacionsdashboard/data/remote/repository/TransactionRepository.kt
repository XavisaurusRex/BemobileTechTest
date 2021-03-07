package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.repository

import cat.devsofthecoast.bemobiletechtest.common.data.remote.RepositoryResponse
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiTransaction

interface TransactionRepository {

    suspend fun getTransactions(): RepositoryResponse<List<ApiTransaction>>

    suspend fun getConverionRates(): RepositoryResponse<List<ApiConversionRate>>

}