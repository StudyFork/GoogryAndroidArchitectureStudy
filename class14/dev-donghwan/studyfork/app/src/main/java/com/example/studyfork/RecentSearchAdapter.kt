package com.example.studyfork

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyfork.databinding.ItemRecentSearchBinding

class RecentSearchAdapter(
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<RecentSearchAdapter.ViewHolder>() {
    private val itemList: ArrayList<String> = ArrayList()

    fun setItems(items: List<String>) {
        this.itemList.clear()
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentSearchAdapter.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recent_search,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecentSearchAdapter.ViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class ViewHolder(private val binding: ItemRecentSearchBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun onBind(item: String) {
            binding.tvTitle.text = item
            binding.root.setOnClickListener{
                onClick(item)
            }
            binding.executePendingBindings()
        }
    }
}