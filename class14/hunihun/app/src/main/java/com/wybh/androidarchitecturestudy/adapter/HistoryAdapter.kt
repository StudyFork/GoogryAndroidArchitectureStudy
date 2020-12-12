package com.wybh.androidarchitecturestudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wybh.androidarchitecturestudy.R
import com.wybh.androidarchitecturestudy.databinding.HistoryItemBinding

class HistoryAdapter(private val itemClickListener: (link: String) -> Unit) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private val wordList: ArrayList<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = DataBindingUtil.inflate<HistoryItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.history_item,
            parent,
            false
        )
        return HistoryViewHolder(binding, itemClickListener)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(wordList[position])
    }

    fun addList(item: List<String>) {
        wordList.addAll(item)
        notifyDataSetChanged()
    }

    class HistoryViewHolder(
        private val binding: HistoryItemBinding,
        private val itemClickListener: (link: String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private var query = ""

        init {
            itemView.setOnClickListener {
                itemClickListener(query)
            }
        }

        fun bind(word: String) {
            binding.run {
                data = word
                executePendingBindings()
            }
            query = word
        }
    }
}