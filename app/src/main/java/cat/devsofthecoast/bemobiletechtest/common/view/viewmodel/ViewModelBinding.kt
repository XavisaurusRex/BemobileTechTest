package cat.devsofthecoast.bemobiletechtest.common.view.viewmodel

import android.view.View
import androidx.databinding.BindingAdapter
import cat.devsofthecoast.bemobiletechtest.common.data.remote.AsyncResult
import cat.devsofthecoast.bemobiletechtest.common.extensions.view.setVisible

object ViewModelBinding {

    @BindingAdapter("app:showWhen")
    @JvmStatic
    fun <T> showWhen(view: View, condition: Boolean?) {
        view.setVisible(condition)
    }

}