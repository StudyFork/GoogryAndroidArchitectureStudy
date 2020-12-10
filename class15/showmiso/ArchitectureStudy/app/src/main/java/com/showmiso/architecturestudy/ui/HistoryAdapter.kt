package com.showmiso.architecturestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.showmiso.architecturestudy.databinding.ItemHistoryBinding

class HistoryAdapter(
    private val listener: OnHistoryClickListener
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private val historyList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HistoryViewHolder(binding)
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

    fun clearHistoryList() {
        historyList.clear()
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(
        private val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var text: String = ""

        init {
            binding.ivDelete.setOnClickListener {
                listener.onHistoryItemClickToDelete(text)
                historyList.remove(text)
                notifyDataSetChanged()
            }
            binding.root.setOnClickListener {
                listener.onHistoryItemClick(text)
            }
        }

        fun bind(text: String) {
            this.text = text

            binding.item = text
            binding.position = adapterPosition + 1
        }
    }

    interface OnHistoryClickListener {
        fun onHistoryItemClick(text: String)
        fun onHistoryItemClickToDelete(text: String)
    }
}