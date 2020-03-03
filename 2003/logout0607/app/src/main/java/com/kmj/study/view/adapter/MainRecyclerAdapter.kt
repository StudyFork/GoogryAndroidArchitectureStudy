package com.kmj.study.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kmj.study.R
import com.kmj.study.dto.MovieDto

class MainRecyclerAdapter (private val context: Context,
                           private val items: MutableList<MovieDto>,
                           private val itemClick: (Any) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)

        return MainViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? MainViewHolder)?.onBind(context, items[position], position)
    }

    inner class MainViewHolder(itemView: View, itemClick: (Any) -> Unit): RecyclerView.ViewHolder(itemView) {
        fun onBind(context: Context, item: MovieDto, position: Int) {
            with(itemView) {

            }
        }
    }
}