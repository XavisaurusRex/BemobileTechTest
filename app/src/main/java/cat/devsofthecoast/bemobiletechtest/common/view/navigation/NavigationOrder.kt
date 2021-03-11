package cat.devsofthecoast.bemobiletechtest.common.view.navigation


import androidx.navigation.NavDirections

sealed class NavigationOrder {
    data class To(val directions: NavDirections) : NavigationOrder()
    object Back : NavigationOrder()
}