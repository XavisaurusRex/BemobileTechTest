package cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cat.devsofthecoast.bemobiletechtest.R
import cat.devsofthecoast.bemobiletechtest.common.extensions.view.setVisible
import cat.devsofthecoast.bemobiletechtest.common.view.BaseFragment
import cat.devsofthecoast.bemobiletechtest.databinding.FragmentTransactionDetailsBinding
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.TransactionDetailsAdapter
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.adapter.dw.TransactionDetailsDataWrapper
import cat.devsofthecoast.bemobiletechtest.feature.transactionsdetails.view.viewmodel.TransactionDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TransactionDetailsFragment : BaseFragment() {

    val args: TransactionDetailsFragmentArgs by navArgs()

    lateinit var binding: FragmentTransactionDetailsBinding

    override val viewModel: TransactionDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.tvIdentifier.text = getString(
            R.string.fragment_details_titles,
            args.transactionDetails.skuRefCode
        )
        viewModel.setRequestParams(args.transactionDetails.skuRefCode)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.transactionDetails.observe(::getLifecycle) { onGetDetailsDataWrappers(it) }
    }

    private fun onGetDetailsDataWrappers(dataWrappers: List<TransactionDetailsDataWrapper>?) {
        binding.rcyTransactionDetails.setVisible(dataWrappers.isNullOrEmpty().not()) {
            binding.rcyTransactionDetails.adapter = TransactionDetailsAdapter(dataWrappers!!)
        }
    }

}