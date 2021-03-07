package cat.devsofthecoast.bemobiletechtest.feature.dashboardapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cat.devsofthecoast.bemobiletechtest.databinding.FragmentDashboardBinding
import cat.devsofthecoast.bemobiletechtest.feature.dashboardapp.view.viewmodel.DashboardViewModel
import cat.devsofthecoast.bemobiletechtest.feature.dashboardapp.view.viewmodel.Movements
import cat.devsofthecoast.bemobiletechtest.feature.movementdetail.view.fragment.args.Details
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    lateinit var binding: FragmentDashboardBinding

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        bindClicks()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
    }

    private fun bindClicks() {
        binding.btnRequestData.setOnClickListener {
            viewModel.requestMovements()
        }

        binding.btnNext.setOnClickListener {
            findNavController().navigate(
                DashboardFragmentDirections.actionFromDashboardToDetails(
                    Details(
                        "Mock Name"
                    )
                )
            )
        }
    }

    private fun setUpObservers() {
        viewModel.movements.observe(::getLifecycle) { showMovements(it) }
    }

    private fun showMovements(movements: Movements?) {
        movements?.let { list ->
            var constructedText = binding.tvMain.text.toString() + "\n\n"
            list.forEach {
                constructedText += it.info + "\n"
            }
            binding.tvMain.text = constructedText
        }
    }

}