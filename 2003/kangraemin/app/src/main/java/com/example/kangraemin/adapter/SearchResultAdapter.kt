package com.example.kangraemin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.Movie
import com.example.kangraemin.databinding.AdapterSearchResultBinding

class SearchResultAdapter :
    RecyclerView.Adapter<SearchResultAdapter.ItemViewHolder>() {

    private val data: MutableList<Movie> = mutableListOf()

    class ItemViewHolder(
        private val binding: AdapterSearchResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movieDetail: Movie) {
            binding.movieDetail = movieDetail
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            binding = AdapterSearchResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<Movie>) {
        this.data.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position])
    }
}