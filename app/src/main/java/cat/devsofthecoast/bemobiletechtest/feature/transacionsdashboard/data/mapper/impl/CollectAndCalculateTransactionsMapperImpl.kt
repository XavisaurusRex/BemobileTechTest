package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.impl

import android.content.Context
import cat.devsofthecoast.bemobiletechtest.R
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.CollectAndCalculateTransactionsMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import dagger.hilt.android.qualifiers.ApplicationContext
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

/**
 * This should be recallable usecase for reusage in future developments
 */
class CollectAndCalculateTransactionsMapperImpl @Inject constructor(
    @ApplicationContext val context: Context
) :
    CollectAndCalculateTransactionsMapper {

    override fun mapTo(from: Pair<ConversionRates, List<Transaction>>): List<TransactionDataWrapper> {

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
        }.map {
            TransactionDataWrapper(
                it,
                it.skuRefCode,
                getFormattedAmount(it.amount, it.currency),
                getFormattedConversionRate(it.conversionRate),
                isConversionRatePositive(it.conversionRate)
            )
        }
    }

    private fun isConversionRatePositive(conversionRate: BigDecimal): Boolean {
        return conversionRate >= BigDecimal.ONE
    }

    private fun getFormattedConversionRate(conversionRate: BigDecimal): String {
        val conversionRateStr = conversionRate.toEngineeringString()
        return if (conversionRateStr.length > 15)
            conversionRateStr.substring(0, 15)
        else conversionRateStr
    }

    private fun getFormattedAmount(amount: BigDecimal, currency: String): String {
        return context.getString(
            R.string.amount_recipient,
            DecimalFormat(context.getString(R.string.view_holder_amount_format))
                .format(
                    amount
                        .setScale(2, RoundingMode.HALF_EVEN)
                ), currency
        )
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