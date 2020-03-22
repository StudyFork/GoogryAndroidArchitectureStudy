package io.github.sooakim.ui.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.github.sooakim.BR
import io.github.sooakim.ui.model.SAPresentation

open class SAViewHolder<VDB : ViewDataBinding, Model : SAPresentation>(
    itemView: View,
    protected val brId: Int = BR.item
) : RecyclerView.ViewHolder(itemView), SAViewHolderLifecycle<Model> {
    protected val viewDataBinding: VDB = DataBindingUtil.bind<VDB>(itemView)!!

    override fun onBind(item: Model) {
        viewDataBinding.setVariable(brId, item)
        viewDataBinding.executePendingBindings()
    }

    override fun onRecycle() {

    }
}