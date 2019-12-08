package com.example.kotlinapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.viewholder.MovieViewHolder
import com.example.kotlinapplication.model.MovieItems

class ListMovieAdapter(
    listener: ItemListener
) :
    RecyclerView.Adapter<MovieViewHolder>() {


    private lateinit var items: List<MovieItems>
    private var mListener: ItemListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_list_item,
                parent,
                false
            )
        )


    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.bind(items[position], mListener)

    }

    fun addAllItems(ListItems: List<MovieItems>) {
        items = ListItems
    }

    interface ItemListener {
        fun onMovieItemClick(movieItems: MovieItems)
    }


}