package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.impl

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.CollectAndCalculateTransactionsMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.ConversionRatesMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.TransactionListMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiTransaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import javax.inject.Inject

/**
 * This should be recallable usecase for reusage in future developments
 */
class CollectAndCalculateTransactionsMapperImpl @Inject constructor(
    private val conversionRatesMapper: ConversionRatesMapper,
    private val transactionsMapper: TransactionListMapper
) : CollectAndCalculateTransactionsMapper {

    override fun mapToBo(from: Pair<List<ApiConversionRate>, List<ApiTransaction>>): List<TransactionDetails> {

        val mapedvalues = hashMapOf<String, Pair<Double, Double>>()

        val conversionRates = conversionRatesMapper.mapToBo(from.first)
        val transactions = transactionsMapper.mapToBo(from.second)

        transactions.forEach {

            val existingEntry = mapedvalues[it.skuRefCode]
            var conversionRate = conversionRates[it.currency, "EUR"]
            if (it.currency == "EUR") {
                conversionRate = 1.0
            }
            if (conversionRate != null) {

                val entry: Pair<Double, Double>
                if (existingEntry == null) {
                    entry = getApiTransactionAmount(
                        it.amount,
                        conversionRate
                    ) to conversionRate
                } else {
                    entry = (existingEntry.first + getApiTransactionAmount(
                        it.amount,
                        conversionRate
                    )) to existingEntry.second * conversionRate
                }

                mapedvalues[it.skuRefCode] = entry
            }
            conversionRate?.let { rate ->
                val entry: Pair<Double, Double>
                if (existingEntry == null) {
                    entry = getApiTransactionAmount(
                        it.amount,
                        rate
                    ) to rate
                } else {
                    entry = (existingEntry.first + getApiTransactionAmount(
                        it.amount,
                        rate
                    )) to existingEntry.second * rate
                }

                mapedvalues[it.skuRefCode] = entry
            }
        }

        return mapedvalues.map {
            TransactionDetails(
                it.key,
                it.value.first,
                "EUR",
                it.value.second
            )
        }
    }

    private fun getApiTransactionAmount(toDouble: Double, conversionRate: Double?): Double {
        // TODO: 3/7/21 DO OP with half round bank
        conversionRate?.let {
            return toDouble * conversionRate
        }
        return toDouble
    }

}