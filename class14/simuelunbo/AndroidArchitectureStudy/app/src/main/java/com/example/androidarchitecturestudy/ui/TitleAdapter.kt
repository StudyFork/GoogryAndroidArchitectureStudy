package com.example.androidarchitecturestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecturestudy.R
import com.example.androidarchitecturestudy.data.model.QueryHistory
import com.example.androidarchitecturestudy.databinding.ItemMovieTitleBinding

class TitleAdapter : RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

    private val list = ArrayList<QueryHistory>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TitleViewHolder {
        val view = DataBindingUtil.inflate<ItemMovieTitleBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_title,
            parent,
            false
        )
        return TitleViewHolder(view)
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.count()

    fun setTitleList(titleList: ArrayList<QueryHistory>) {
        list.clear()
        list.addAll(titleList)
        notifyDataSetChanged()
    }

    class TitleViewHolder(private val binding: ItemMovieTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(query: QueryHistory) {
            binding.item = query
            binding.executePendingBindings()
        }
    }
}