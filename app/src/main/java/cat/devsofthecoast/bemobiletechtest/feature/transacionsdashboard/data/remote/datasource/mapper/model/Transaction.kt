package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.model

import java.math.BigDecimal

data class Transaction(
    val skuRefCode: String,
    val amount: BigDecimal,
    val currency: String
)
