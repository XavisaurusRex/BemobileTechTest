package cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.dw

import cat.devsofthecoast.bemobiletechtest.common.adapter.dw.BaseDataWrapper
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class TransactionDetailsDataWrapper(
    val transactionId: String,
    val formattedAmount: String
) : BaseDataWrapper() {

    companion object {
        const val VIEW_TYPE_TRANSACTION = 135
    }

    @IgnoredOnParcel
    override val viewType: Int = VIEW_TYPE_TRANSACTION

}