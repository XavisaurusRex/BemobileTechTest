package cat.devsofthecoast.bemobiletechtest.feature.movementdetail.view.fragment.args

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Details(
    val name: String
) : Parcelable