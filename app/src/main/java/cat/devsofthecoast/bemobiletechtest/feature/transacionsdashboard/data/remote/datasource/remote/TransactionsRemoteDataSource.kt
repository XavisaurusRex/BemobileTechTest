package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.remote

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiTransaction

interface TransactionsRemoteDataSource {

    suspend fun getTransactions(): List<ApiTransaction>

    suspend fun getConversionRates(): List<ApiConversionRate>

}