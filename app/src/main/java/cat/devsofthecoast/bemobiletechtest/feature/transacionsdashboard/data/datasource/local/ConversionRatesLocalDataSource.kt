package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates

interface ConversionRatesLocalDataSource {
    suspend fun getConversionRates(): ConversionRates

    suspend fun saveConversionRates(conversionRates: ConversionRates)
}
