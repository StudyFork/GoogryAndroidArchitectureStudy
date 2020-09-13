package com.hong.architecturestudy.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.data.model.MovieData

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    private val items = mutableListOf<MovieData>()
    lateinit var onClick: ((MovieData) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(parent).apply {
            itemView.setOnClickListener {
                val item = items[absoluteAdapterPosition]
                onClick.invoke(item)
            }
        }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    fun setData(item: List<MovieData>) {
        items.clear()
        items.addAll(item)
        notifyDataSetChanged()
    }
}