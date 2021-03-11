package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionDetails(
    val skuRefCode: String,
    val amount: Double,
    val amountCurrency: String,
    val conversionRate: Double
) : Parcelable