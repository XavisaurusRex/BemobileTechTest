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
        viewModel.loading.observe(::getLifecycle) { showLoading(it) }
        viewModel.error.observe(::getLifecycle) { showError(it) }
        viewModel.navigation.observe(::getLifecycle) { event ->
            event?.getContentIfNotHandled()?.let {
                navigate(it)
            }
        }
    }

    abstract fun showLoading(shouldShow: Boolean?)

    abstract fun showError(shouldShow: Boolean?)

    private fun navigate(order: NavigationOrder) {
        when (order) {
            is NavigationOrder.To -> findNavController().navigate(order.directions)
            is NavigationOrder.Back -> findNavController().navigateUp()
        }
    }
}