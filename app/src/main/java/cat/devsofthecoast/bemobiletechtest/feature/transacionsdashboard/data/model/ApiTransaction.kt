package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.model

import com.google.gson.annotations.SerializedName

data class ApiTransaction(
    @SerializedName("sku")
    val skuStockRef: String,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: String
)
