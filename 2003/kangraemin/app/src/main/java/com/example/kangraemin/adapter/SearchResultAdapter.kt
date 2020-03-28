package com.example.kangraemin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kangraemin.databinding.AdapterSearchResultBinding
import com.example.kangraemin.model.remote.datamodel.MovieDetail

class SearchResultAdapter :
    RecyclerView.Adapter<SearchResultAdapter.ItemViewHolder>() {

    private val data = ArrayList<MovieDetail>()

    class ItemViewHolder(
        private val binding: AdapterSearchResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movieDetail: MovieDetail) {
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

    fun setData(data: ArrayList<MovieDetail>) {
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