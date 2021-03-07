package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.ws

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiTransaction
import retrofit2.Response
import retrofit2.http.GET

interface TransactionsWs {

    @GET("/rates.json")
    suspend fun getConversionRates(): Response<List<ApiConversionRate>>

    @GET("/transactions.json")
    suspend fun getTransactions(): Response<List<ApiTransaction>>

}