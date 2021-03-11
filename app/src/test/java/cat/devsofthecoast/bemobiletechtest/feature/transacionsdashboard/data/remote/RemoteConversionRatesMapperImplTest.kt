package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.mapper.impl.RemoteConversionRatesMapperImpl
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.ConversionRates
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.model.ApiConversionRate
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RemoteConversionRatesMapperImplTest {

    @Test
    fun `verify that ConversionMapper works correctly in standar situation`() {

        // Given
        // Nothing to mock


        // When
        val apiConversionRates = listOf(
            ApiConversionRate("CAD", "AUD", "1.2"),
            ApiConversionRate("EUR", "CAD", "0.83"),
            ApiConversionRate("CAD", "USD", "1.45"),
            ApiConversionRate("USD", "CAD", "0.69"),
            ApiConversionRate("EUR", "AUD", "1.29"),
            ApiConversionRate("AUD", "EUR", "0.78")
        )

        // Then
        Assert.assertEquals(
            ConversionRates(
                mapOf(
                    "CAD" to "AUD" to 1.2,
                    "EUR" to "CAD" to 0.83,
                    "CAD" to "USD" to 1.45,
                    "USD" to "CAD" to 0.69,
                    "EUR" to "AUD" to 1.29,
                    "AUD" to "EUR" to 0.78,
                    "CAD" to "EUR" to 0.9359999999999999,
                    "USD" to "EUR" to 0.64584,
                )
            ), RemoteConversionRatesMapperImpl().mapTo(apiConversionRates)
        )
    }

    @Test
    fun `verify that ConversionMapper works correctly in more complex situation`() {

        // Given
        // Nothing to mock


        // When
        val apiConversionRates = listOf(
            ApiConversionRate("Z", "A", "1.819"),
            ApiConversionRate("A", "M", "1.873"),
            ApiConversionRate("G", "I", "1.623"),
            ApiConversionRate("F", "G", "1.132"),
            ApiConversionRate("I", "P", "1.688"),
            ApiConversionRate("A", "B", "1.715"),
            ApiConversionRate("A", "Q", "1.345"),
            ApiConversionRate("B", "C", "1.091"),
            ApiConversionRate("B", "F", "1.934"),
            ApiConversionRate("C", "R", "1.315"),
            ApiConversionRate("C", "J", "1.386"),
            ApiConversionRate("J", "L", "1.016"),
            ApiConversionRate("J", "K", "1.967"),
            ApiConversionRate("J", "O", "1.609"),
            ApiConversionRate("F", "H", "1.861"),
            ApiConversionRate("H", "R", "1.656"),
            ApiConversionRate("I", "O", "1.66"),
            ApiConversionRate("O", "EUR", "1.66")
        )


        // Then
        Assert.assertEquals(
            ConversionRates(
                mapOf(
                    "A" to "EUR" to 6.9265382586246,
                    "F" to "G" to 1.132,
                    "B" to "C" to 1.091,
                    "J" to "K" to 1.967,
                    "J" to "L" to 1.016,
                    "F" to "H" to 1.861,
                    "B" to "F" to 1.934,
                    "J" to "O" to 1.609,
                    "Z" to "EUR" to 12.599373092438148,
                    "H" to "R" to 1.656,
                    "J" to "EUR" to 2.67094,
                    "F" to "EUR" to 5.062687521599999,
                    "A" to "B" to 1.715,
                    "B" to "EUR" to 4.038797818439999,
                    "G" to "I" to 1.623,
                    "I" to "O" to 1.66,
                    "Z" to "A" to 1.819,
                    "I" to "P" to 1.688,
                    "C" to "J" to 1.386,
                    "A" to "M" to 1.873,
                    "C" to "R" to 1.315,
                    "A" to "Q" to 1.345,
                    "O" to "EUR" to 1.66,
                    "I" to "EUR" to 2.7556,
                    "G" to "EUR" to 4.472338799999999,
                    "C" to "EUR" to 3.701922839999999
                )
            ), RemoteConversionRatesMapperImpl().mapTo(apiConversionRates)
        )
    }

    @Test
    fun `verify that ConversionMapper works when there are no input data`() {

        // Given
        // Nothing to mock


        // When
        val apiConversionRates = emptyList<ApiConversionRate>()

        // Then
        Assert.assertEquals(
            ConversionRates(emptyMap()),
            RemoteConversionRatesMapperImpl().mapTo(apiConversionRates)
        )
    }
}