package sample.nackun.com.studyfirst.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseRecyclerView {

    abstract class BaseAdapter<ITEM : Any, B : ViewDataBinding>(
        @LayoutRes private val layoutRes: Int,
        private val bindingVariabledId: Int
    ) : RecyclerView.Adapter<BaseViewHolder<B>>() {

        protected val items = mutableListOf<ITEM>()

        fun setItems(items: List<ITEM>) {
            items.let {
                this.items.run {
                    clear()
                    addAll(it)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            object : BaseViewHolder<B>(
                layoutRes = layoutRes,
                parent = parent,
                bindingVariabledId = bindingVariabledId
            ) {}

        override fun getItemCount() = items.size

        override fun onBindViewHolder(baseViewHolder: BaseViewHolder<B>, position: Int) =
            items[position].let(baseViewHolder::bind)
    }

    abstract class BaseViewHolder<B : ViewDataBinding>(
        @LayoutRes layoutRes: Int,
        parent: ViewGroup?,
        private val bindingVariabledId: Int
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent?.context)
            .inflate(layoutRes, parent, false)
    ) {
        val binding: B = DataBindingUtil.bind(itemView)!!

        fun bind(item: Any?) {
            binding.setVariable(bindingVariabledId, item)
        }
    }

}