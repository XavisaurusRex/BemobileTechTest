package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.repository

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.repository.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.repository.model.ApiTransaction
import retrofit2.Call
import retrofit2.http.GET

interface TransactionsRepository {

    @GET("/rates.json")
    fun getConversionRates(): Call<List<ApiConversionRate>>

    @GET("/transactions.json")
    fun getTransactions(): Call<List<ApiTransaction>>

}