package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model

import java.math.BigDecimal

data class Transaction(
    val skuRefCode: String,
    val amount: BigDecimal,
    val currency: String
)
