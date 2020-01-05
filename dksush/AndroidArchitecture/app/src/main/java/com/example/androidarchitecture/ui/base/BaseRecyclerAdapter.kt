package com.example.androidarchitecture.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T, H : BaseViewHolder<T>>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, H>(diffCallback) {

    private val items: MutableList<T> = mutableListOf()

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    open fun setData(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        submitList(items)

    }
}


abstract class BaseViewHolder<T, B : ViewDataBinding>(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup,
    private val bindingVariableId: Int?

) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        layoutResId,
        parent,
        false
    )
) {

    protected val binding: B = DataBindingUtil.bind(itemView)!! // 뷰홀더에서 itemView로 바인딩 객체 생성.
    abstract fun bind(item: T)
}

//abstract class BaseViewHolder<T>(
//    itemView: View
//) : RecyclerView.ViewHolder(itemView) {
//    abstract fun bind(item: T)
//}