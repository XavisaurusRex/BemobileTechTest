package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import cat.devsofthecoast.bemobiletechtest.R
import cat.devsofthecoast.bemobiletechtest.common.adapter.vh.BaseViewHolder
import cat.devsofthecoast.bemobiletechtest.databinding.ViewholderTransactionBinding
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.listener.TransactionsAdapterListener

class TransactionViewHolder(parent: ViewGroup) :
    BaseViewHolder<TransactionDataWrapper, TransactionsAdapterListener>(
        ViewholderTransactionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).root
    ) {
    private val binding: ViewholderTransactionBinding by lazy {
        ViewholderTransactionBinding.bind(itemView)
    }

    override fun bindViewHolder(
        dataWrapper: TransactionDataWrapper,
        listener: TransactionsAdapterListener?,
        position: Int
    ) {

        binding.tvTransactionId.text = dataWrapper.item.skuRefCode

        binding.tvToAmount.text =
            "${String.format("%.2f", dataWrapper.item.amount)} ${dataWrapper.item.amountCurrency}"

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val color = context.getColor(
                if (dataWrapper.item.conversionRate > 1.0) {
                    binding.ivConversionRateIndicator.setImageLevel(0)
                    R.color.viewholder_conversion_positive
                } else {
                    binding.ivConversionRateIndicator.setImageLevel(1)
                    R.color.viewholder_conversion_negative
                }
            )
            binding.tvConversionRate.setTextColor(color)
            binding.ivConversionRateIndicator.setColorFilter(color)
        }

        binding.tvConversionRate.text = String.format("%.6f", dataWrapper.item.conversionRate)

        itemView.setOnClickListener {
            listener?.onTransactionClicked(dataWrapper.item)
        }
    }


}