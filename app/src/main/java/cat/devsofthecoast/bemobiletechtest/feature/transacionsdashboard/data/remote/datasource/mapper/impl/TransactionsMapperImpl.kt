package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.impl

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.TransactionsMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiTransaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Currency
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import javax.inject.Inject

class TransactionsMapperImpl @Inject constructor() : TransactionsMapper {

    override fun mapToBo(from: Pair<List<ApiConversionRate>, List<ApiTransaction>>): List<TransactionDataWrapper> {
        val result = arrayListOf<TransactionDataWrapper>()

        val mapedvalues = hashMapOf<String, Double>()

        val appConversionRate: Map<Pair<String, String>, Double> = getAppConversionRates(from.first)

        from.second.forEach {

            val amount = mapedvalues[it.skuStockRef]
            if (amount == null) {
                mapedvalues[it.skuStockRef] =
                    getApiTransactionAmount(
                        it.amount.toDouble(),
                        appConversionRate[it.currency to "EUR"]
                    )
            } else {
                mapedvalues[it.skuStockRef] = amount + getApiTransactionAmount(
                    it.amount.toDouble(),
                    appConversionRate[it.currency to "EUR"]
                )
            }
        }

        mapedvalues.keys.forEach {
            result.add(
                TransactionDataWrapper(
                    TransactionDetails(
                        it,
                        0.0,
                        Currency.EUR,
                        mapedvalues[it]!!,
                        Currency.EUR,
                        0.0
                    )
                )
            )
            mapedvalues[it]
        }

        return result
    }

    private fun getApiTransactionAmount(toDouble: Double, conversionRate: Double?): Double {
        // TODO: 3/7/21 DO OP with half round bank
        conversionRate?.let {
            return toDouble * conversionRate
        }
        return toDouble
    }

    private fun getAppConversionRates(apiConversionRates: List<ApiConversionRate>): Map<Pair<String, String>, Double> {
        val map = HashMap<Pair<String, String>, Double>()
        apiConversionRates.forEach {
            map[it.fromCurrency to it.toCurrency] = it.rate.toDouble()
        }


        val remainingRelationShips = arrayListOf<Pair<Pair<String, String>, Double>>()
        map.keys.forEach { pair ->
            val fromCurrency = pair.first
            if(fromCurrency != "EUR" && map[fromCurrency to "EUR"] == null) {

                val middleConversion = map.keys.firstOrNull() {
                    map[fromCurrency to it.first] != null && it.second == "EUR"
                }

                if(middleConversion == null){
                    // TODO: 3/7/21 NEED TO IMPLEMENTS RECURSIVE FUNCTION

                } else {
                    remainingRelationShips.add((fromCurrency to "EUR") to (map[pair]!! + map[middleConversion]!!))
                }
            }
        }

        remainingRelationShips.forEach {
            map[it.first] = it.second
        }
        return map
    }

}