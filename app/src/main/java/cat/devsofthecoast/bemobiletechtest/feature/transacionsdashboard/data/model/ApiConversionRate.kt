package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.model

import com.google.gson.annotations.SerializedName

data class ApiConversionRate(
    @SerializedName("from")
    val fromCurrency: String,
    @SerializedName("to")
    val toCurrency: String,
    @SerializedName("rate")
    val rate: String
)
