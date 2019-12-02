package com.example.kotlinapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.viewholder.ListViewHolder
import com.example.kotlinapplication.model.MovieItems

class ListAdapter(val itemListener: ItemListener,val items:List<MovieItems>,val context: Context) :RecyclerView.Adapter<ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_list_item,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(items.get(position),itemListener,position)
    }

    interface ItemListener{
        fun onItemClick(movieItems: MovieItems)
    }


}