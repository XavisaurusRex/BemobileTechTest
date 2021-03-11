package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.remote

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction

interface TransactionsRemoteDataSource {

    suspend fun getTransactions(): List<Transaction>

    suspend fun getConversionRates(): ConversionRates

}