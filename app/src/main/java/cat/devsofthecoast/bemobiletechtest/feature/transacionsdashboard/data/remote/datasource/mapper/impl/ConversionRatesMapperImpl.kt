package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.impl

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.ConversionRatesMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.model.ConversionRates
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiConversionRate
import java.math.BigDecimal
import javax.inject.Inject


class ConversionRatesMapperImpl @Inject constructor() : ConversionRatesMapper {

    override fun mapToBo(from: List<ApiConversionRate>): ConversionRates {
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