package kr.schoolsharing.coinhelper.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
    private val parent: ViewGroup,
    private val bindingVariableId: Int? = null
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
) {

    private val binding: B? = DataBindingUtil.bind(itemView)

    fun bind(item: Any?) {
        binding?.run {
            bindingVariableId?.let {
                setVariable(it, item)
            }
            executePendingBindings()
        }
    }

}