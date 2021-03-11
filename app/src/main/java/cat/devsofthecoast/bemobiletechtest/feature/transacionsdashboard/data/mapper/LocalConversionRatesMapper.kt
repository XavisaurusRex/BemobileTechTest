package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper

import cat.devsofthecoast.bemobiletechtest.common.data.local.model.ConversionRateDbo
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates

interface LocalConversionRatesMapper : ModelMapper<List<ConversionRateDbo>, ConversionRates> {
    fun mapFrom(conversionRates: ConversionRates): List<ConversionRateDbo>
}