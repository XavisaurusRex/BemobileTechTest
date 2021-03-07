package cat.devsofthecoast.bemobiletechtest.feature.dashboardapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cat.devsofthecoast.bemobiletechtest.databinding.FragmentDashboardBinding
import cat.devsofthecoast.bemobiletechtest.feature.movementdetail.view.fragment.args.Details
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment: Fragment() {

    lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
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
        // no op
    }

}