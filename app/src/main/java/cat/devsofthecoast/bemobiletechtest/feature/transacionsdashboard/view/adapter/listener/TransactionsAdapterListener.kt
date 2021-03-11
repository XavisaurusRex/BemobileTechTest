package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.listener

import cat.devsofthecoast.bemobiletechtest.common.adapter.listener.BaseAdapterListener
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper

interface TransactionsAdapterListener : BaseAdapterListener<TransactionDataWrapper> {

    fun onTransactionClicked(transactionDetails: TransactionDetails)

}
