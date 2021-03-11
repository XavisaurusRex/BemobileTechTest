package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local.impl

import cat.devsofthecoast.bemobiletechtest.common.data.local.ConversionRatesDao
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.datasource.local.ConversionRatesLocalDataSource
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.LocalConversionRatesMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates
import javax.inject.Inject

class ConversionRatesLocalDataSourceImpl @Inject constructor(
    private val conversionRatesDao: ConversionRatesDao,
    private val localConversionRatesMapper: LocalConversionRatesMapper
) : ConversionRatesLocalDataSource {

    override suspend fun getConversionRates(): ConversionRates {
        return localConversionRatesMapper.mapTo(conversionRatesDao.getAllTConversionRates())
    }

    override suspend fun saveConversionRates(conversionRates: ConversionRates) {
        conversionRatesDao.clearTable()
        localConversionRatesMapper
            .mapFrom(conversionRates)
            .forEach { conversionRateDbo ->
                conversionRatesDao.saveConversionRate(conversionRateDbo)
            }

    }
}