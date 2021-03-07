package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw

import cat.devsofthecoast.bemobiletechtest.common.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class TransactionDataWrapper(
    val item: TransactionDetails
    ) : BaseDataWrapper() {

    companion object {
        const val VIEW_TYPE_TRANSACTION = 135
    }

    @IgnoredOnParcel
    override val viewType: Int = VIEW_TYPE_TRANSACTION

}