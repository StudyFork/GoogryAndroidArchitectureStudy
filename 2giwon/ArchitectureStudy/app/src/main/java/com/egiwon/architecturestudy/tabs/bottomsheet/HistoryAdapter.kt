package com.egiwon.architecturestudy.tabs.bottomsheet

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.base.BaseViewHolder
import kotlinx.android.synthetic.main.search_history_item.view.*

class HistoryAdapter(
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<HistoryViewHolder>() {

    private val list = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(parent).apply {
            itemView.setOnClickListener {
                onClick(adapterPosition)
            }
        }


    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun setList(history: List<String>) {
        list.run {
            clear()
            addAll(history)
        }

        notifyDataSetChanged()
    }
}

class HistoryViewHolder(
    parent: ViewGroup,
    @LayoutRes
    private val resourceId: Int = R.layout.search_history_item
) : BaseViewHolder<String>(
    parent,
    resourceId
) {
    private val tvQuery = itemView.query

    override fun bind(item: String) {
        tvQuery.text = item
    }

}

