package com.example.kotlinapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.viewholder.MovieViewHolder
import com.example.kotlinapplication.model.MovieItems

class ListBlogAdapter(
    listener: ItemListener,
    val items: List<MovieItems>,
    val context: Context?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mListener: ItemListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.movie_list_item,
                parent,
                false
            )
        )


    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            holder.bind(items.get(position), mListener)
        }
    }

    interface ItemListener {
        fun onMovieItemClick(movieItems: MovieItems)
    }


}