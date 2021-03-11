package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.impl

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.RemoteConversionRatesMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.utils.GraphUtils
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates
import java.math.BigDecimal
import javax.inject.Inject


class RemoteConversionRatesMapperImpl @Inject constructor() : RemoteConversionRatesMapper {

    override fun mapTo(from: List<ApiConversionRate>): ConversionRates {
        val conversionMapper: HashMap<Pair<String, String>, BigDecimal?> = hashMapOf()

        from.forEach {
            conversionMapper[it.fromCurrency to it.toCurrency] = it.rate.toBigDecimal()
        }

        return generateRemainingRelations(conversionMapper)
    }


    private fun generateRemainingRelations(currentRelations: HashMap<Pair<String, String>, BigDecimal?>): ConversionRates {
        // TODO: 3/10/21 ACTUALLY WE ONLY NEED TO CALCULATE EURO REMAINING RELATIONS

        val graphOfConversion = GraphUtils.Graph(currentRelations.keys.toList())

        currentRelations.keys.filter {
            it.first != EURO_CURRENCY &&
                    currentRelations[it.first to EURO_CURRENCY] == null
        }.forEach { pair ->
            val conversionCandidate = pair.first
            GraphUtils.findPathInGraph(
                graphOfConversion,
                conversionCandidate,
                EURO_CURRENCY
            )?.let { pathToEur ->
                var calculedRate = "1".toBigDecimal()
                pathToEur.forEach {
                    calculedRate *= currentRelations[it]!!
                }

                currentRelations[conversionCandidate to EURO_CURRENCY] = calculedRate
            }
        }

        return ConversionRates(currentRelations)
    }

    companion object {
        const val EURO_CURRENCY = "EUR"
    }

}