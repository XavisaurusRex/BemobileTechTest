package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.remote.impl

import android.content.Context
import cat.devsofthecoast.bemobiletechtest.BuildConfig
import cat.devsofthecoast.bemobiletechtest.common.extensions.context.readAssetFileToString
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.remote.TransactionsRemoteDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.error.RemoteErrorManagement
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiTransaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.ws.TransactionsWs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class TransactionsRemoteDataSourceImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val transactionsWs: TransactionsWs
) : TransactionsRemoteDataSource {
    override suspend fun getTransactions(): List<ApiTransaction> = RemoteErrorManagement.wrap {
        if (BuildConfig.MOCK_WS_CALLS) {
            val itemType = object : TypeToken<List<ApiTransaction>>() {}.type
            Gson().fromJson(
                context.readAssetFileToString("mock/02_transactions.json"),
                itemType
            )
        } else {
            val remoteTransactions = transactionsWs.getTransactions()

            remoteTransactions.body()!!
        }
    }

    override suspend fun getConversionRates(): List<ApiConversionRate> =
        RemoteErrorManagement.wrap {
            if (BuildConfig.MOCK_WS_CALLS) {
                val itemType = object : TypeToken<List<ApiConversionRate>>() {}.type
                Gson().fromJson(
                    context.readAssetFileToString("mock/01_conversionrates.json"),
                    itemType
                )
            } else {
                val remoteTransactions = transactionsWs.getConversionRates()

                remoteTransactions.body()!!
            }
        }


}