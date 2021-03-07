package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionDetails(
    val skuRefCode: String,
    val fromAmount: Double,
    val fromAmountCurrency: Currency,
    val toAmount: Double,
    val toAmountCurrency: Currency,
    val conversionRate: Double
) : Parcelable