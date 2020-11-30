package com.wybh.androidarchitecturestudy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wybh.androidarchitecturestudy.databinding.HistoryItemBinding

class HistoryAdapter(private val itemClickListener: (link: String) -> Unit) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private lateinit var binding: HistoryItemBinding
    private val wordList: ArrayList<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.history_item,
            parent,
            false
        )
        return HistoryViewHolder(binding)
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

    inner class HistoryViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                itemClickListener(wordList[adapterPosition])
            }
        }

        fun bind(word: String) {
            binding.run {
                data = word
                executePendingBindings()
            }
        }
    }
}