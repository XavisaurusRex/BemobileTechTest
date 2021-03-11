package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.RemoteConversionRatesMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.RemoteTransactionListMapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.impl.CollectAndCalculateTransactionsMapperImpl
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.Transaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.model.ApiConversionRate
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.model.ApiTransaction
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import org.junit.Assert.assertEquals
import org.junit.Test

class CollectAndCalculateTransactionsMapperImplTest {

    @Test
    fun `verify that given Api Model mapper returns `() {
        // Given
        val collectAndCalculateTransactionsMapperImpl = CollectAndCalculateTransactionsMapperImpl(
            createMockConversionRatesMapper(),
            createMockTransactionListMapper()
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
            listOf(
                TransactionDetails(
                    "B2009",
                    42.46,
                    "EUR",
                    2.0
                ),
                TransactionDetails(
                    "R2008",
                    35.9,
                    "EUR",
                    2.0
                ),
                TransactionDetails(
                    "M2007",
                    69.14,
                    "EUR",
                    2.0
                ),
                TransactionDetails(
                    "T2006",
                    27.63,
                    "EUR",
                    2.0
                )
            ),
            collectAndCalculateTransactionsMapperImpl.mapTo(
                apiConversionRates to apiTransactions
            )
        )
    }

    private fun createMockTransactionListMapper(): RemoteTransactionListMapper {
        return object : RemoteTransactionListMapper {
            override fun mapTo(from: Pair<ConversionRates, List<Transaction>>): List<Transaction> {
                return listOf(
                    Transaction(
                        "T2006",
                        10.00,
                        "USD"
                    ),
                    Transaction(
                        "M2007", 34.57,
                        "CAD"
                    ),
                    Transaction(
                        "R2008",
                        17.95,
                        "USD"
                    ),
                    Transaction(
                        "T2006",
                        7.63,
                        "EUR"
                    ),
                    Transaction(
                        "B2009",
                        21.23,
                        "USD"
                    ),
                )
            }

        }

    }

    private fun createMockConversionRatesMapper(): RemoteConversionRatesMapper {
        return object : RemoteConversionRatesMapper {
            override fun mapTo(from: Pair<ConversionRates, List<Transaction>>): ConversionRates {
                return ConversionRates(
                    mapOf(
                        "CAD" to "AUD" to 2.0,
                        "EUR" to "CAD" to 2.0,
                        "CAD" to "USD" to 2.0,
                        "USD" to "CAD" to 2.0,
                        "EUR" to "AUD" to 2.0,
                        "AUD" to "EUR" to 2.0,
                        "CAD" to "EUR" to 2.0,
                        "USD" to "EUR" to 2.0
                    )
                )
            }

        }
    }
}