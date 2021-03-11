package cat.devsofthecoast.bemobiletechtest.common.view.viewmodel

import android.view.View
import androidx.databinding.BindingAdapter
import cat.devsofthecoast.bemobiletechtest.common.data.remote.AsyncResult
import cat.devsofthecoast.bemobiletechtest.common.extensions.view.setVisible

object ViewModelBinding {

    @BindingAdapter("app:showWhenSuccess")
    @JvmStatic
    fun <T> showWhenSuccess(view: View, asyncResult: AsyncResult<T>?) {
        view.setVisible(asyncResult?.status == AsyncResult.Status.SUCCESS)
    }

    @BindingAdapter("app:showWhenLoading")
    @JvmStatic
    fun <T> showWhenLoading(view: View, asyncResult: AsyncResult<T>?) {
        view.setVisible(asyncResult?.status == AsyncResult.Status.LOADING)
    }

    @BindingAdapter("app:showWhenError")
    @JvmStatic
    fun <T> showWhenError(view: View, asyncResult: AsyncResult<T>?) {
        view.setVisible(asyncResult?.status == AsyncResult.Status.ERROR)
    }

}