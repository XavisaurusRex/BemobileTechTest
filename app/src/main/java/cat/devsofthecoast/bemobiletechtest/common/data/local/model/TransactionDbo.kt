package cat.devsofthecoast.bemobiletechtest.common.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "transaction_history")
data class TransactionDbo(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    val skuRefCode: String,
    val amount: BigDecimal,
    val currency: String
)