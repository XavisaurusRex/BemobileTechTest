package cat.devsofthecoast.bemobiletechtest.common.extensions.view

import android.view.View

fun View?.setVisible(condition: Boolean?, doWhenVisible: () -> Unit = {}) {
    this?.visibility = if (condition == true) {
        doWhenVisible()
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View?.setInvisible(condition: Boolean?) {
    this?.visibility = if (condition == true) {
        View.INVISIBLE
    } else {
        View.VISIBLE
    }
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.show() {
    this?.visibility = View.VISIBLE
}