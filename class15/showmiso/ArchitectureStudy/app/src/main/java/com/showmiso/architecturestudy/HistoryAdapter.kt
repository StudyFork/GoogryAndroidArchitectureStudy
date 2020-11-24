package com.showmiso.architecturestudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(
    private val listener: OnHistoryClickListener
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private val historyList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val text = historyList[position]
        holder.bind(text)
    }

    override fun getItemCount(): Int = historyList.size

    fun setHistoryList(list: List<String>?) {
        list?.let {
            historyList.clear()
            historyList.addAll(it)
            notifyDataSetChanged()
        }
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(text: String) {
            itemView.tv_index.text = (adapterPosition + 1).toString()
            itemView.tv_text.text = text
            itemView.setOnClickListener {
                listener.onItemClick(text)
            }
            itemView.iv_delete.setOnClickListener {
                listener.onDeleteItem(text)
                historyList.remove(text)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    interface OnHistoryClickListener {
        fun onItemClick(text: String)
        fun onDeleteItem(text: String)
    }
}