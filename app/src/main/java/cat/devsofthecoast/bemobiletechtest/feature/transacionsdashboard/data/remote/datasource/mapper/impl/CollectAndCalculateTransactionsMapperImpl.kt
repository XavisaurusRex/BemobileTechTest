package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.impl

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.ConversionRatesMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.TransactionsMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiTransaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import javax.inject.Inject

/**
 * This should be recallable usecase for reusage in future developments
 */
class CollectAndCalculateTransactionsMapperImpl @Inject constructor(
    private val conversionRatesMapper: ConversionRatesMapper
) : TransactionsMapper {

    override fun mapToBo(from: Pair<List<ApiConversionRate>, List<ApiTransaction>>): List<TransactionDetails> {
        val result = arrayListOf<TransactionDetails>()

        val mapedvalues = hashMapOf<String, Pair<Double, Double>>()

        val conversionRates = conversionRatesMapper.mapToBo(from.first)


        from.second.forEach {

            val existingEntry = mapedvalues[it.skuStockRef]
            var conversionRate = conversionRates[it.currency, "EUR"]
            if (it.currency == "EUR") {
                conversionRate = 1.0
            }
            if (conversionRate != null) {

                val entry: Pair<Double, Double>
                if (existingEntry == null) {
                    entry = getApiTransactionAmount(
                        it.amount.toDouble(),
                        conversionRate
                    ) to conversionRate
                } else {
                    entry = (existingEntry.first + getApiTransactionAmount(
                        it.amount.toDouble(),
                        conversionRate
                    )) to existingEntry.second * conversionRate
                }

                mapedvalues[it.skuStockRef] = entry
            }
            conversionRate?.let { rate ->
                val entry: Pair<Double, Double>
                if (existingEntry == null) {
                    entry = getApiTransactionAmount(
                        it.amount.toDouble(),
                        rate
                    ) to rate
                } else {
                    entry = (existingEntry.first + getApiTransactionAmount(
                        it.amount.toDouble(),
                        rate
                    )) to existingEntry.second * rate
                }

                mapedvalues[it.skuStockRef] = entry
            }
        }

        mapedvalues.forEach {
            result.add(
                TransactionDetails(
                    it.key,
                    it.value.first,
                    "EUR",
                    it.value.second
                )
            )
        }

        return result
    }

    private fun getAppConversionRates(first: List<ApiConversionRate>): Map<Pair<String, String>, Double> {
        return mapOf()
    }

    private fun getApiTransactionAmount(toDouble: Double, conversionRate: Double?): Double {
        // TODO: 3/7/21 DO OP with half round bank
        conversionRate?.let {
            return toDouble * conversionRate
        }
        return toDouble
    }

}