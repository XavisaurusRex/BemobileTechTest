package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.impl

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.CollectAndCalculateTransactionsMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import java.math.BigDecimal
import javax.inject.Inject

/**
 * This should be recallable usecase for reusage in future developments
 */
class CollectAndCalculateTransactionsMapperImpl @Inject constructor() :
    CollectAndCalculateTransactionsMapper {

    override fun mapTo(from: Pair<ConversionRates, List<Transaction>>): List<TransactionDetails> {

        val mapedvalues = hashMapOf<String, Pair<BigDecimal, BigDecimal>>()

        val conversionRates = from.first
        val transactions = from.second

        transactions.forEach {

            val existingEntry = mapedvalues[it.skuRefCode]
            var conversionRate = conversionRates[it.currency, "EUR"]
            if (it.currency == "EUR") {
                conversionRate = "1".toBigDecimal()
            }
            if (conversionRate != null) {
                val entry: Pair<BigDecimal, BigDecimal>
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
                val entry: Pair<BigDecimal, BigDecimal>
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

    private fun getApiTransactionAmount(
        toDouble: BigDecimal,
        conversionRate: BigDecimal?
    ): BigDecimal {
        conversionRate?.let {
            return toDouble * conversionRate
        }
        return toDouble
    }

}