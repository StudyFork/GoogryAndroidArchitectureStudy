package ado.sabgil.studyproject.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T : Any, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    val items: MutableList<T> by lazy { mutableListOf<T>() }

    fun updateItem(items: List<T>) {
        this.items.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = this.items.size

}