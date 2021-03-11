package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.impl

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.ConversionRatesMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.model.ConversionRates
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.model.ApiTransaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import org.junit.Assert.assertEquals
import org.junit.Test

class CollectAndCalculateTransactionsMapperImplTest {

    @Test
    fun `verify that given Api Model mapper returns `() {
        // Given
        val collectAndCalculateTransactionsMapperImpl = CollectAndCalculateTransactionsMapperImpl(
            createMockConversionRatesMapper()
        )
        // When

        val apiConversionRates = listOf(
            ApiConversionRate("EUR", "USD", "1.359"),
            ApiConversionRate("CAD", "EUR", "0.732"),
            ApiConversionRate("USD", "EUR", "0.736"),
            ApiConversionRate("EUR", "CAD", "1.366")
        )

        val apiTransactions = listOf(
            ApiTransaction(
                "T2006",
                "10.00",
                "USD"
            ),
            ApiTransaction(
                "M2007",
                "34.57",
                "CAD"
            ),
            ApiTransaction(
                "R2008",
                "17.95",
                "USD"
            ),
            ApiTransaction(
                "T2006",
                "7.63",
                "EUR"
            ),

            ApiTransaction(
                "B2009",
                "21.23",
                "USD"
            )
        )

        assertEquals(
            arrayListOf(
                TransactionDetails()
            ),
            collectAndCalculateTransactionsMapperImpl.mapToBo(
                apiConversionRates to apiTransactions
            )
        )
    }

    private fun createMockConversionRatesMapper(): ConversionRatesMapper {
        return object : ConversionRatesMapper {
            override fun mapToBo(from: List<ApiConversionRate>): ConversionRates {
                ConversionRates(
                    mapOf(
                        "CAD" to "AUD" to 1.2,
                        "EUR" to "CAD" to 0.83,
                        "CAD" to "USD" to 1.45,
                        "USD" to "CAD" to 0.69,
                        "EUR" to "AUD" to 1.29,
                        "AUD" to "EUR" to 0.78,
                        "CAD" to "EUR" to 0.9359999999999999
                        "USD" to "EUR" to 0.64584,
                    )
                )
            }

        }
    }
}