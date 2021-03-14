package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import cat.devsofthecoast.bemobiletechtest.R
import cat.devsofthecoast.bemobiletechtest.common.extensions.view.setVisible
import cat.devsofthecoast.bemobiletechtest.common.view.BaseFragment
import cat.devsofthecoast.bemobiletechtest.databinding.FragmentTransactionsDashboardBinding
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.domain.model.TransactionDetails
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.TransactionsAdapter
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.dw.TransactionDataWrapper
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.adapter.listener.TransactionsAdapterListener
import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.view.viewmodel.TransactionsDashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionsDashboardFragment : BaseFragment(), TransactionsAdapterListener {

    lateinit var binding: FragmentTransactionsDashboardBinding

    override val viewModel: TransactionsDashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionsDashboardBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragmentdashboard_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuRequestFromRemote) {
            viewModel.requestMovements(true)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpObservers() {
        viewModel.transactions.observe(::getLifecycle) { onGetTransactions(it) }
    }

    private fun onGetTransactions(transactionsResult: List<TransactionDataWrapper>?) {
        binding.rcyTransactions.setVisible(transactionsResult.isNullOrEmpty().not()) {
            binding.rcyTransactions.adapter = TransactionsAdapter(this, transactionsResult!!)
        }
    }

    override fun onTransactionClicked(transactionDetails: TransactionDetails) {
        viewModel.goToTransactionDetails(transactionDetails)
    }
}
