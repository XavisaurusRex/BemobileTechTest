package cat.devsofthecoast.bemobiletechtest.common.adapter.vh

import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import cat.devsofthecoast.bemobiletechtest.common.adapter.dw.BaseDataWrapper
import cat.devsofthecoast.bemobiletechtest.common.adapter.listener.BaseAdapterListener

abstract class BaseViewHolder<DW : BaseDataWrapper, LT : BaseAdapterListener<DW>>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    protected val context: Context = itemView.context

    abstract fun bindViewHolder(dataWrapper: DW, listener: LT?, position: Int)

    open fun onViewAttachedToWindow(listener: LT?) {}

}