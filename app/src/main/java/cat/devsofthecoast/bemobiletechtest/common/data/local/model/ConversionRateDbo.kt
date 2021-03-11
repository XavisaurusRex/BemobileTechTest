package cat.devsofthecoast.bemobiletechtest.common.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "conversion_rates")
data class ConversionRateDbo(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    val fromCurrency: String,
    val toCurrency: String,
    val rate: BigDecimal
)