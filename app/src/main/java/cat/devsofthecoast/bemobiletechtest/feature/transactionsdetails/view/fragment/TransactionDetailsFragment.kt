package cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import cat.devsofthecoast.bemobiletechtest.R
import cat.devsofthecoast.bemobiletechtest.databinding.FragmentTransactionDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import java.text.DecimalFormat


@AndroidEntryPoint
class TransactionDetailsFragment : Fragment() {

    val args: TransactionDetailsFragmentArgs by navArgs()

    lateinit var binding: FragmentTransactionDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        binding.tvMain.text = args.transactionDetails.skuRefCode
        binding.tvAmount.text = getString(
            R.string.amount_recipient,
            DecimalFormat(getString(R.string.view_holder_amount_format))
                .format(
                    args.transactionDetails.amount
                        .setScale(2, RoundingMode.HALF_EVEN)
                ),
            args.transactionDetails.currency
        )
        binding.tvSec.text = args.transactionDetails.conversionRate.toEngineeringString()
    }

    private fun setUpObservers() {
        // no op
    }

}