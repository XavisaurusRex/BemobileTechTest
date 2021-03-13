package cat.devsofthecoast.bemobiletechtest.common.extensions.allscopes

import android.util.Log

fun logDebug(message: String, tr: Throwable? = null) {
    Log.d("BeMobileTechTest", message, tr)
}

fun logWaring(message: String, tr: Throwable? = null) {
    Log.w("BeMobileTechTest", message, tr)
}