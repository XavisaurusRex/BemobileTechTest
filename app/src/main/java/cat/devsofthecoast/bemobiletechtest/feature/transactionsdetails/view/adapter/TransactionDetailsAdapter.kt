package cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter

import android.view.ViewGroup
import cat.devsofthecoast.bemobiletechtest.common.adapter.BaseAdapter
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.dw.TransactionDetailsDataWrapper
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.listener.TransactionDetailsAdapterListener
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.vh.TransactionDetailViewHolder

class TransactionDetailsAdapter(
    dataWrappers: List<TransactionDetailsDataWrapper>
) :
    BaseAdapter<TransactionDetailsDataWrapper, TransactionDetailViewHolder, TransactionDetailsAdapterListener>() {

    override val data: ArrayList<TransactionDetailsDataWrapper> = arrayListOf()

    init {
        data.addAll(dataWrappers)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionDetailViewHolder =
        TransactionDetailViewHolder(parent)


}