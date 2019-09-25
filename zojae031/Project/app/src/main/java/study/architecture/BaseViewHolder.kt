package study.architecture

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<B : ViewDataBinding>(
    layoutId: Int,
    parents: ViewGroup,
    private val bindingVariableId: Int? = null
) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parents.context)
            .inflate(layoutId, parents, false)
    ) {

    private val binding: B? = DataBindingUtil.bind(itemView)

    fun bind(item: Any?) {
        binding?.apply {
            bindingVariableId?.let {
                setVariable(it, item)
            }
            executePendingBindings()
        }
    }
}