package kr.schoolsharing.coinhelper.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<ITEM : Any, B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
    private val bindingId: Int? = null
) : RecyclerView.Adapter<BaseViewHolder<B>>() {

    private val itemList = mutableListOf<ITEM>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<B> =
        object : BaseViewHolder<B>(layoutId, parent, bindingId) {}

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BaseViewHolder<B>, position: Int) {
        holder.bind(itemList[position])
    }

    fun setTickerList(tickerList: List<ITEM>?) {
        if (tickerList != null) {
            itemList.clear()
            itemList.addAll(tickerList)
            notifyDataSetChanged()
        }
    }
}