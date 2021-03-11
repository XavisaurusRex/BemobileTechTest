package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model

import java.math.BigDecimal

data class ConversionRates(
    private val innerMap: Map<Pair<String, String>, BigDecimal?>
) {
    operator fun get(fromCurrency: String, toCurrency: String) =
        innerMap[fromCurrency to toCurrency]
}
