package cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import cat.devsofthecoast.bemobiletechtest.common.adapter.vh.BaseViewHolder
import cat.devsofthecoast.bemobiletechtest.databinding.ViewholderTransactionDetailBinding
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.listener.TransactionDetailsAdapterListener
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.dw.TransactionDetailsDataWrapper

class TransactionDetailViewHolder(parent: ViewGroup) :
    BaseViewHolder<TransactionDetailsDataWrapper, TransactionDetailsAdapterListener>(
        ViewholderTransactionDetailBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).root
    ) {
    private val binding: ViewholderTransactionDetailBinding by lazy {
        ViewholderTransactionDetailBinding.bind(itemView)
    }

    override fun bindViewHolder(
        dataWrapper: TransactionDetailsDataWrapper,
        listener: TransactionDetailsAdapterListener?,
        position: Int
    ) {
        binding.tvSkuRefCode.text = dataWrapper.transactionId
        binding.tvTransactionAmount.text = dataWrapper.formattedAmount
    }


}