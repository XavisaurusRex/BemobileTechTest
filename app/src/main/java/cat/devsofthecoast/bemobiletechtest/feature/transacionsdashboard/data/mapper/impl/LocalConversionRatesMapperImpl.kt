package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.impl

import cat.devsofthecoast.bemobiletechtest.common.data.local.model.ConversionRateDbo
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.LocalConversionRatesMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates
import java.math.BigDecimal
import javax.inject.Inject


class LocalConversionRatesMapperImpl @Inject constructor() : LocalConversionRatesMapper {

    override fun mapFrom(conversionRates: ConversionRates): List<ConversionRateDbo> {
        return conversionRates.keys.map {
            ConversionRateDbo(
                null,
                it.first,
                it.second,
                conversionRates[it.first, it.second] ?: BigDecimal("1")
            )
        }
    }

    override fun mapTo(from: List<ConversionRateDbo>): ConversionRates {
        val rates: MutableMap<Pair<String, String>, BigDecimal?> = mutableMapOf()
        from.map {
            rates[it.fromCurrency to it.toCurrency] = it.rate
        }
        return ConversionRates(rates)
    }
}