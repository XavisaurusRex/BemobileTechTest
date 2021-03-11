package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter

import android.view.ViewGroup
import cat.devsofthecoast.bemobiletechtest.common.adapter.BaseAdapter
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.listener.TransactionsAdapterListener
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.vh.TransactionViewHolder

class TransactionsAdapter() :
    BaseAdapter<TransactionDataWrapper, TransactionViewHolder, TransactionsAdapterListener>() {

    override val data: ArrayList<TransactionDataWrapper> = arrayListOf()

    constructor(
        listener: TransactionsAdapterListener,
        dataWrappers: List<TransactionDataWrapper>
    ) : this() {
        this.listener = listener
        data.addAll(dataWrappers)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionViewHolder =
        TransactionViewHolder(parent)


}