package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.model

import java.math.BigDecimal

data class ConversionRates(
    private val innerMap: Map<Pair<String, String>, BigDecimal?>
) {
    operator fun get(fromCurrency: String, toCurrency: String) =
        innerMap[fromCurrency to toCurrency]
}
