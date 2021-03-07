package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cat.devsofthecoast.bemobiletechtest.common.data.remote.AsyncResult
import cat.devsofthecoast.bemobiletechtest.databinding.FragmentTransactionsDashboardBinding
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.TransactionsAdapter
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.listener.TransactionsAdapterListener
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.viewmodel.TransactionsDashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionsDashboardFragment : Fragment(), TransactionsAdapterListener {

    lateinit var binding: FragmentTransactionsDashboardBinding

    private val viewModel: TransactionsDashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionsDashboardBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.apiTransaction.observe(::getLifecycle) { observeTransactions(it) }
    }

    private fun observeTransactions(transactionsResult: AsyncResult<List<TransactionDataWrapper>>) {
        val transactions =
            transactionsResult.data.takeIf { transactionsResult.status == AsyncResult.Status.SUCCESS }
        transactions?.let { dataWrappers ->
            binding.rcyTransactions.adapter = TransactionsAdapter(this, dataWrappers)

        }
    }

    override fun onTransactionClicked(transactionDetails: TransactionDetails) {
        findNavController().navigate(
            TransactionsDashboardFragmentDirections.actionFromDashboardToDetails(
                transactionDetails
            )
        )
    }
}