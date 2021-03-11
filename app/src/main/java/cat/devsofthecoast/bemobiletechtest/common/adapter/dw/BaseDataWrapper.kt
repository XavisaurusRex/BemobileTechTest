package cat.devsofthecoast.bemobiletechtest.common.adapter.dw

import android.os.Parcelable

abstract class BaseDataWrapper : Parcelable {
    abstract val viewType: Int
}