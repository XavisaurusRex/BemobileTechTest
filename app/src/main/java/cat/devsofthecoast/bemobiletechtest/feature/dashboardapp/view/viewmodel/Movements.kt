package cat.devsofthecoast.bemobiletechtest.feature.dashboardapp.view.viewmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

typealias Movements = List<Movement>

@Parcelize
data class Movement(
    val info: String
) : Parcelable
