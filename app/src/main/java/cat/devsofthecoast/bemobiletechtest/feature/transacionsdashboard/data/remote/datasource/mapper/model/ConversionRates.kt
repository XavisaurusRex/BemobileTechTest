package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.model

data class ConversionRates(
    private val innerMap: Map<Pair<String, String>, Double?>
) {
    operator fun get(fromCurrency: String, toCurrency: String) =
        innerMap[fromCurrency to toCurrency]
}
