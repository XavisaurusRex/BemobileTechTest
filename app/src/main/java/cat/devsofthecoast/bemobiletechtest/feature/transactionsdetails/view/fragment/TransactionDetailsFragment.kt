package cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import cat.devsofthecoast.bemobiletechtest.databinding.FragmentTransactionDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

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
    }

    private fun setUpObservers() {
        // no op
    }

}