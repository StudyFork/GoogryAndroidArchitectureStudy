package com.example.myproject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.data.model.Items
import com.example.myproject.databinding.MovieItemBinding
import com.example.myproject.ui.MovieAdapter.ViewHolder


class MovieAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val movieArrayList = arrayListOf<Items>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<MovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieArrayList[position])
    }

    override fun getItemCount() = movieArrayList.size

    fun clearItems() {
        movieArrayList.clear()
        notifyDataSetChanged()
    }

    fun clearAndAddItems(items: List<Items>) {
        movieArrayList.clear()
        movieArrayList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Items) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}
