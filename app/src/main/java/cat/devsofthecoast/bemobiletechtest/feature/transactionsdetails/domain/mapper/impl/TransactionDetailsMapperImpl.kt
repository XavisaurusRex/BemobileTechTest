package cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.domain.mapper.impl

import android.content.Context
import cat.devsofthecoast.bemobiletechtest.R
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.domain.mapper.TransactionDetailsMapper
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.dw.TransactionDetailsDataWrapper
import dagger.hilt.android.qualifiers.ApplicationContext
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class TransactionDetailsMapperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : TransactionDetailsMapper {

    override fun mapTo(from: List<Transaction>): List<TransactionDetailsDataWrapper> {
        return from.map {
            TransactionDetailsDataWrapper(
                it.skuRefCode,
                getFormattedAmount(it.amount, it.currency)
            )
        }
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

}