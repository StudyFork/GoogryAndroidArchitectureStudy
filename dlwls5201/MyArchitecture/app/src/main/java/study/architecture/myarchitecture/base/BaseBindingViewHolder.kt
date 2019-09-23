package study.architecture.myarchitecture.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingViewHolder<B : ViewDataBinding>(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup?,
    private val bindingId: Int?
) : BaseViewHolder(layoutRes, parent) {

    private val binding: B = DataBindingUtil.bind(itemView)!!

    override fun onBindView(item: Any?) {
        binding.run {
            if (bindingId != null) {
                setVariable(bindingId, item)
            }
            executePendingBindings()
        }
    }
}