package cat.devsofthecoast.bemobiletechtest.common.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cat.devsofthecoast.bemobiletechtest.common.view.navigation.NavigationOrder
import cat.devsofthecoast.bemobiletechtest.common.view.viewmodel.BaseViewModel

abstract class BaseFragment : Fragment() {

    abstract val viewModel: BaseViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpBaseObservers()
    }

    private fun setUpBaseObservers() {
        viewModel.navigation.observe(::getLifecycle) { event ->
            event?.getContentIfNotHandled()?.let {
                navigate(it)
            }
        }
    }

    private fun navigate(order: NavigationOrder) {
        when (order) {
            is NavigationOrder.To -> findNavController().navigate(order.directions)
            is NavigationOrder.Back -> findNavController().navigateUp()
        }
    }
}