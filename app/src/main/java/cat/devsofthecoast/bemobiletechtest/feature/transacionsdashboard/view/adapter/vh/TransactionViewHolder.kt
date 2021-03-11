package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.vh

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import cat.devsofthecoast.bemobiletechtest.R
import cat.devsofthecoast.bemobiletechtest.common.adapter.vh.BaseViewHolder
import cat.devsofthecoast.bemobiletechtest.databinding.ViewholderTransactionBinding
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.listener.TransactionsAdapterListener
import java.math.RoundingMode
import java.text.DecimalFormat

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

        binding.tvToAmount.text = itemView.resources.getString(
            R.string.amount_recipient,
            DecimalFormat(itemView.resources.getString(R.string.view_holder_amount_format))
                .format(
                    dataWrapper.item.amount
                        .setScale(2, RoundingMode.HALF_EVEN)
                ),
            dataWrapper.item.currency
        )

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val color = context.getColor(
                if (dataWrapper.item.conversionRate.compareTo("1".toBigDecimal()) == -1) {
                    binding.ivConversionRateIndicator.setImageLevel(1)
                    R.color.viewholder_conversion_negative
                } else {
                    binding.ivConversionRateIndicator.setImageLevel(0)
                    R.color.viewholder_conversion_positive
                }
            )
            binding.tvConversionRate.setTextColor(color)
            binding.ivConversionRateIndicator.setColorFilter(color)
        }

        Log.d("TransactionVH", "${dataWrapper.item.skuRefCode} -> ${dataWrapper.item.amount} -> ${dataWrapper.item.conversionRate} \n")
        binding.tvConversionRate.text = dataWrapper.item.conversionRate.toString().substring(0 )

        itemView.setOnClickListener {
            listener?.onTransactionClicked(dataWrapper.item)
        }
    }


}