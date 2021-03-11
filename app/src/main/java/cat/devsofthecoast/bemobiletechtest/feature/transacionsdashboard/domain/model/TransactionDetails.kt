package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class TransactionDetails(
    val skuRefCode: String,
    val amount: BigDecimal,
    val currency: String,
    val conversionRate: BigDecimal
) : Parcelable